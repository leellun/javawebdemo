package com.newland.cms.system.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.newland.cms.domain.Role;

public interface RoleService extends IService<Role> {
    List<Role> findUserRole(String userName);
}
