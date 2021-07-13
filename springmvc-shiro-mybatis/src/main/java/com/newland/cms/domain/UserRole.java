package com.newland.cms.domain;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("t_user_role")
public class UserRole implements Serializable {

	private static final long serialVersionUID = -3166012934498268403L;

	private Long userId;

	private Long roleId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

}