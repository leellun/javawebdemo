package com.newland.cms.system.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newland.cms.domain.Role;
import com.newland.cms.system.mapper.RoleMapper;
import com.newland.cms.system.service.RoleService;
@Service("roleService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

	@Override
	public List<Role> findUserRole(String userName) {
		return baseMapper.findUserRole(userName);
	}

}
