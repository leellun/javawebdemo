package com.newland.manager.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author leellun
 * @since 2021-07-03
 */
@TableName("t_user")
public class User implements Serializable {


    /**
     * 用户ID
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long ID;

    /**
     * 用户名
     */
    private String USERNAME;

    /**
     * 密码
     */
    private String PASSWORD;

    /**
     * 邮箱
     */
    private String EMAIL;

    /**
     * 联系电话
     */
    private String MOBILE;

    /**
     * 状态 0锁定 1有效
     */
    private String STATUS;

    /**
     * 创建时间
     */
    private LocalDateTime CREATE_TIME;

    /**
     * 修改时间
     */
    private LocalDateTime MODIFY_TIME;

    /**
     * 最近访问时间
     */
    private LocalDateTime LAST_LOGIN_TIME;


    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getMOBILE() {
        return MOBILE;
    }

    public void setMOBILE(String MOBILE) {
        this.MOBILE = MOBILE;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public LocalDateTime getCREATE_TIME() {
        return CREATE_TIME;
    }

    public void setCREATE_TIME(LocalDateTime CREATE_TIME) {
        this.CREATE_TIME = CREATE_TIME;
    }

    public LocalDateTime getMODIFY_TIME() {
        return MODIFY_TIME;
    }

    public void setMODIFY_TIME(LocalDateTime MODIFY_TIME) {
        this.MODIFY_TIME = MODIFY_TIME;
    }

    public LocalDateTime getLAST_LOGIN_TIME() {
        return LAST_LOGIN_TIME;
    }

    public void setLAST_LOGIN_TIME(LocalDateTime LAST_LOGIN_TIME) {
        this.LAST_LOGIN_TIME = LAST_LOGIN_TIME;
    }

    @Override
    public String toString() {
        return "User{" +
        "ID=" + ID +
        ", USERNAME=" + USERNAME +
        ", PASSWORD=" + PASSWORD +
        ", EMAIL=" + EMAIL +
        ", MOBILE=" + MOBILE +
        ", STATUS=" + STATUS +
        ", CREATE_TIME=" + CREATE_TIME +
        ", MODIFY_TIME=" + MODIFY_TIME +
        ", LAST_LOGIN_TIME=" + LAST_LOGIN_TIME +
        "}";
    }
}
