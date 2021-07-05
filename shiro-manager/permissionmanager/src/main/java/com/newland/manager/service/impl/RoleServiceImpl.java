package com.newland.manager.service.impl;

import com.newland.manager.domain.Role;
import com.newland.manager.domain.User;
import com.newland.manager.mapper.RoleMapper;
import com.newland.manager.mapper.UserMapper;
import com.newland.manager.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author leellun
 * @since 2021-07-03
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Set<String> getUserRoles(String username) {
        User user = userMapper.selectUser(username);
        return new HashSet<>(baseMapper.selectRoles(user.getID()));
    }
}
