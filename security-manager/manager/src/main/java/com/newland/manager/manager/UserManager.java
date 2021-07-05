package com.newland.manager.manager;

import com.newland.manager.domain.User;
import com.newland.manager.service.IMenuService;
import com.newland.manager.service.IRoleService;
import com.newland.manager.service.IUserService;
import com.newland.manager.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserManager {
    private final static long EXPIRE_TIME = 60 * 60 * 1000;
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IMenuService menuService;
    @Autowired
    private RedisUtil redisUtil;

    public Set<String> getRoles(String username) {
        redisUtil.del(getRoleKey(username));
        Set<String> roleSet = (Set<String>) redisUtil.get(getRoleKey(username));
        if (roleSet == null) {
            roleSet = roleService.getUserRoles(username);
            redisUtil.set(getRoleKey(username), roleSet, EXPIRE_TIME);
        }
        return roleSet;
    }

    public Set<String> getPermissions(String username) {
        redisUtil.del(getPermissionKey(username));
        Set<String> permissions = (Set<String>) redisUtil.get(getPermissionKey(username));
        if (permissions == null) {
            permissions = new HashSet(menuService.getPermissions(username));
            redisUtil.set(getPermissionKey(username), permissions, EXPIRE_TIME);
        }
        return permissions;
    }

    private String getRoleKey(String username) {
        return String.format("role-%s", username);
    }

    private String getPermissionKey(String username) {
        return String.format("permission-%s", username);
    }

    public User getUser(String username) {
        String key = String.format("user-%s", username);
        redisUtil.del(key);
        User user = (User) redisUtil.get(key);
        if (user == null) {
            user = userService.getUser(username);
            if (user != null) {
                redisUtil.set(key, user, EXPIRE_TIME);
            }
        }
        return user;
    }
}
