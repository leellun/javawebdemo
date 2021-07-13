package com.newland.cms.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.newland.cms.domain.QueryRequest;
import com.newland.cms.domain.User;


public interface UserService extends IService<User> {
    /**
     * 通过用户名查找用户
     *
     * @param username username
     * @return user
     */
    User findByName(String username);
    /**
     * 查询用户详情，包括基本信息，用户角色，用户部门
     *
     * @param user user
     * @param queryRequest queryRequest
     * @return IPage
     */
    IPage<User> findUserDetail(User user, QueryRequest queryRequest);
}
