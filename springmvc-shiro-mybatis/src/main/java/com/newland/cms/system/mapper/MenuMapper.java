package com.newland.cms.system.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.newland.cms.domain.Menu;

public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> findUserPermissions(String userName);

    List<Menu> findUserMenus(String userName);

    /**
     * 查找当前菜单/按钮关联的用户 ID
     *
     * @param menuId menuId
     * @return 用户 ID集合
     */
    List<String> findUserIdsByMenuId(String menuId);
}