package com.newland.cms.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newland.cms.domain.Menu;
import com.newland.cms.domain.RouterMeta;
import com.newland.cms.domain.Tree;
import com.newland.cms.domain.VueRouter;
import com.newland.cms.system.mapper.MenuMapper;
import com.newland.cms.system.service.MenuService;
import com.newland.cms.utils.TreeUtil;

@Service("menuService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
	@Override
	public List<Menu> findUserPermissions(String username) {
		return this.baseMapper.findUserPermissions(username);
	}

	@Override
	public Map<String, Object> findMenus(Menu menu) {
		Map<String, Object> result = new HashMap<>();
		try {
			LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
			findMenuCondition(queryWrapper, menu);
			List<Menu> menus = baseMapper.selectList(queryWrapper);

			List<Tree<Menu>> trees = new ArrayList<>();
			List<String> ids = new ArrayList<>();
			buildTrees(trees, menus, ids);

			result.put("ids", ids);
			if ("1".equals(menu.getType())) {
				result.put("rows", trees);
			} else {
				Tree<Menu> menuTree = TreeUtil.build(trees);
				result.put("rows", menuTree);
			}

			result.put("total", menus.size());
		} catch (NumberFormatException e) {
			log.error("查询菜单失败", e);
			result.put("rows", null);
			result.put("total", 0);
		}
		return result;
	}

	private void findMenuCondition(LambdaQueryWrapper<Menu> queryWrapper, Menu menu) {
		if (!StringUtils.isEmpty(menu.getMenuName())) {
			queryWrapper.eq(Menu::getMenuName, menu.getMenuName());
		}
		if (!StringUtils.isEmpty(menu.getType())) {
			queryWrapper.eq(Menu::getType, menu.getType());
		}
		if (!StringUtils.isEmpty(menu.getCreateTimeFrom()) && !StringUtils.isEmpty(menu.getCreateTimeTo())) {
			queryWrapper.ge(Menu::getCreateTime, menu.getCreateTimeFrom()).le(Menu::getCreateTime,
					menu.getCreateTimeTo());
		}
	}

	private void buildTrees(List<Tree<Menu>> trees, List<Menu> menus, List<String> ids) {
		menus.forEach(menu -> {
			ids.add(menu.getMenuId().toString());
			Tree<Menu> tree = new Tree<>();
			tree.setId(menu.getMenuId().toString());
			tree.setKey(tree.getId());
			tree.setParentId(menu.getParentId().toString());
			tree.setText(menu.getMenuName());
			tree.setTitle(tree.getText());
			tree.setIcon(menu.getIcon());
			tree.setComponent(menu.getComponent());
			tree.setCreateTime(menu.getCreateTime());
			tree.setModifyTime(menu.getModifyTime());
			tree.setPath(menu.getPath());
			tree.setOrder(menu.getOrderNum());
			tree.setPermission(menu.getPerms());
			tree.setType(menu.getType());
			trees.add(tree);
		});
	}

    public ArrayList<VueRouter<Menu>> getUserRouters(String username) {
        List<VueRouter<Menu>> routes = new ArrayList<>();
        List<Menu> menus = this.baseMapper.findUserMenus(username);
        menus.forEach(menu -> {
            VueRouter<Menu> route = new VueRouter<>();
            route.setId(menu.getMenuId().toString());
            route.setParentId(menu.getParentId().toString());
            route.setIcon(menu.getIcon());
            route.setPath(menu.getPath());
            route.setComponent(menu.getComponent());
            route.setName(menu.getMenuName());
            route.setMeta(new RouterMeta(true, null));
            routes.add(route);
        });
        return TreeUtil.buildVueRouter(routes);
    }
}
