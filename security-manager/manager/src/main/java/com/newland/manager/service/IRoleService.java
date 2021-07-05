package com.newland.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.newland.manager.domain.Role;

import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author leellun
 * @since 2021-07-03
 */
public interface IRoleService extends IService<Role> {
    Set<String> getUserRoles(String username);
}
