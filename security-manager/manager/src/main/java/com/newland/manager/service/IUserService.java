package com.newland.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.newland.manager.common.Response;
import com.newland.manager.domain.User;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author leellun
 * @since 2021-07-03
 */
public interface IUserService extends IService<User> {

    Response login(String username, String password);

    User getUser(String username);

    Response info(String username);

    Response list(String username);
}
