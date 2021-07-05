package com.newland.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.newland.manager.domain.Menu;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author leellun
 * @since 2021-07-03
 */
public interface IMenuService extends IService<Menu> {

    List<String> getPermissions(String username);
}
