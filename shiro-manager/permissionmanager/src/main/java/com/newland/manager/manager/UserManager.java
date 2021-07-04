package com.newland.manager.manager;

import com.newland.manager.service.IMenuService;
import com.newland.manager.service.IRoleService;
import com.newland.manager.service.IUserService;
import com.newland.manager.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UserManager {
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IMenuService menuService;
    @Autowired
    private RedisUtil redisUtil;
    public Set<String> getRoles(String username){
        Set<String> roleSet = (Set<String>) redisUtil.get(username);
        if (roleSet == null) {
            roleSet= roleService.get
        }
    }
}
