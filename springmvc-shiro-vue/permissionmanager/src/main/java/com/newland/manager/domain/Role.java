package com.newland.manager.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author leellun
 * @since 2021-07-03
 */
@TableName("t_role")
public class Role implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String role_name;

    private String status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Role{" +
        "id=" + id +
        ", role_name=" + role_name +
        ", status=" + status +
        "}";
    }
}
