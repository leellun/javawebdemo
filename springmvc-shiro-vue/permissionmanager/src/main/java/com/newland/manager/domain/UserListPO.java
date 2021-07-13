package com.newland.manager.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserListPO {
    private int id;
    private int status;
    private String loginName;
    private String name;
    private String realName;
    private String mobile;
    private String address;
    private String email;
    private long lastLoginTime;
    private List<UserRolePO> erpMemberRoles=new ArrayList<>();

}
