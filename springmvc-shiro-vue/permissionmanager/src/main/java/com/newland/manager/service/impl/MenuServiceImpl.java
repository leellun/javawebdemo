package com.newland.manager.service.impl;

import com.newland.manager.domain.Menu;
import com.newland.manager.domain.Role;
import com.newland.manager.domain.User;
import com.newland.manager.manager.UserManager;
import com.newland.manager.mapper.MenuMapper;
import com.newland.manager.mapper.RoleMapper;
import com.newland.manager.mapper.UserMapper;
import com.newland.manager.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author leellun
 * @since 2021-07-03
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {
    @Autowired
    private UserManager userManager;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<String> getPermissions(String username) {
        User user = userManager.getUser(username);
        List<Role> roleList = roleMapper.selectRoleList(user.getID());
        List<Long> roleIds = roleList.stream().mapToLong(Role::getId).boxed().collect(Collectors.toList());
        List<String> menus = menuMapper.selectMenus(roleIds);
        return menus;
    }
}
