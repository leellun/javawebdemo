package com.newland.cms.system.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.newland.cms.domain.Menu;
import com.newland.cms.domain.VueRouter;

public interface MenuService extends IService<Menu> {
	List<Menu> findUserPermissions(String username);

	Map<String, Object> findMenus(Menu menu);

	public ArrayList<VueRouter<Menu>> getUserRouters(String username);
}
