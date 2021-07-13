package com.newland.cms.domain;

import java.io.Serializable;
public class DeptUsers implements Serializable {
    private static final long serialVersionUID = -5615190273698747063L;
    private Long deptId;
    private String userIds;
	public Long getDeptId() {
		return deptId;
	}
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	public String getUserIds() {
		return userIds;
	}
	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}
    
}
