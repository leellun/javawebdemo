package com.newland.manager.service;

import com.newland.manager.common.Response;
import com.newland.manager.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author leellun
 * @since 2021-07-03
 */
public interface IUserService extends IService<User> {

    Response login(String username, String password);
}
