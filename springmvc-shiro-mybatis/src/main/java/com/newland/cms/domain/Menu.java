package com.newland.cms.domain;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("t_menu")
public class Menu implements Serializable {

	private static final long serialVersionUID = 7187628714679791771L;

	public static final String TYPE_MENU = "0";

	public static final String TYPE_BUTTON = "1";

	@TableId(value = "MENU_ID", type = IdType.AUTO)
	private Long menuId;

	private Long parentId;

	private String menuName;

	private String path;

	private String component;

	private String perms;

	private String icon;

	private String type;

	private Double orderNum;

	private Date createTime;

	private Date modifyTime;

	private transient String createTimeFrom;
	private transient String createTimeTo;

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public String getPerms() {
		return perms;
	}

	public void setPerms(String perms) {
		this.perms = perms;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Double orderNum) {
		this.orderNum = orderNum;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getCreateTimeFrom() {
		return createTimeFrom;
	}

	public void setCreateTimeFrom(String createTimeFrom) {
		this.createTimeFrom = createTimeFrom;
	}

	public String getCreateTimeTo() {
		return createTimeTo;
	}

	public void setCreateTimeTo(String createTimeTo) {
		this.createTimeTo = createTimeTo;
	}

}