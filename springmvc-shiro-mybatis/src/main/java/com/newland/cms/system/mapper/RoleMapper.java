package com.newland.cms.system.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.newland.cms.domain.Role;

public interface RoleMapper extends BaseMapper<Role> {
	
	List<Role> findUserRole(String userName);
	
}