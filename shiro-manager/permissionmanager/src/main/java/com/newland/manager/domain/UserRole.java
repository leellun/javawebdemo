package com.newland.manager.domain;

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
@TableName("t_user_role")
public class UserRole implements Serializable {


    /**
     * 用户ID
     */
    private Long USER_ID;

    /**
     * 角色ID
     */
    private Long ROLE_ID;


    public Long getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(Long USER_ID) {
        this.USER_ID = USER_ID;
    }

    public Long getROLE_ID() {
        return ROLE_ID;
    }

    public void setROLE_ID(Long ROLE_ID) {
        this.ROLE_ID = ROLE_ID;
    }

    @Override
    public String toString() {
        return "User_role{" +
        "USER_ID=" + USER_ID +
        ", ROLE_ID=" + ROLE_ID +
        "}";
    }
}
