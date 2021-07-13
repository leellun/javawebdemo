package com.newland.cms.system.controller;

import java.util.ArrayList;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newland.cms.domain.Menu;
import com.newland.cms.domain.VueRouter;
import com.newland.cms.system.service.MenuService;

@RestController
@CrossOrigin
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;
    @GetMapping("/{username}")
    public ArrayList<VueRouter<Menu>> getUserRouters(@PathVariable String username) {
        return this.menuService.getUserRouters(username);
    }
    @RequiresPermissions("menu:view")
    public Map<String, Object> menuList(Menu menu) {
        return this.menuService.findMenus(menu);
    }
}
