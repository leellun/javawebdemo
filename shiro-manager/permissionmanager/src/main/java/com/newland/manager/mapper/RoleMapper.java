package com.newland.manager.mapper;

import com.newland.manager.domain.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Set;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author leellun
 * @since 2021-07-03
 */
public interface RoleMapper extends BaseMapper<Role> {
    Set<String> selectRoles(String username);
}
