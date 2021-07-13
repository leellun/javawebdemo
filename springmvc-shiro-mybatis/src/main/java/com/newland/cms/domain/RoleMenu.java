package com.newland.cms.domain;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("t_role_menu")
public class RoleMenu implements Serializable {
	
	private static final long serialVersionUID = -7573904024872252113L;

    private Long roleId;

    private Long menuId;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
}