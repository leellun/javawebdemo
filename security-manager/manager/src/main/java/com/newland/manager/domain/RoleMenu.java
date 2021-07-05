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
@TableName("t_role_menu")
public class RoleMenu implements Serializable {
    @TableId(value = "ID", type = IdType.AUTO)
    private Long ID;
    private Long ROLE_ID;

    private Long MENU_ID;

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Long getID() {
        return ID;
    }

    public Long getROLE_ID() {
        return ROLE_ID;
    }

    public void setROLE_ID(Long ROLE_ID) {
        this.ROLE_ID = ROLE_ID;
    }

    public Long getMENU_ID() {
        return MENU_ID;
    }

    public void setMENU_ID(Long MENU_ID) {
        this.MENU_ID = MENU_ID;
    }

    @Override
    public String toString() {
        return "Role_menu{" +
        "ROLE_ID=" + ROLE_ID +
        ", MENU_ID=" + MENU_ID +
        "}";
    }
}
