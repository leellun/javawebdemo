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
@TableName("t_menu")
public class Menu implements Serializable {


    /**
     * 菜单/按钮ID
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long ID;

    /**
     * 上级菜单ID
     */
    private Long PARENT_ID;

    /**
     * 菜单/按钮名称
     */
    private String MENU_NAME;

    /**
     * 对应路由组件component
     */
    private String COMPONENT;

    /**
     * 排序
     */
    private Double ORDER_NUM;

    /**
     * 创建时间
     */
    private LocalDateTime CREATE_TIME;

    /**
     * 修改时间
     */
    private LocalDateTime MODIFY_TIME;


    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Long getPARENT_ID() {
        return PARENT_ID;
    }

    public void setPARENT_ID(Long PARENT_ID) {
        this.PARENT_ID = PARENT_ID;
    }

    public String getMENU_NAME() {
        return MENU_NAME;
    }

    public void setMENU_NAME(String MENU_NAME) {
        this.MENU_NAME = MENU_NAME;
    }

    public String getCOMPONENT() {
        return COMPONENT;
    }

    public void setCOMPONENT(String COMPONENT) {
        this.COMPONENT = COMPONENT;
    }

    public Double getORDER_NUM() {
        return ORDER_NUM;
    }

    public void setORDER_NUM(Double ORDER_NUM) {
        this.ORDER_NUM = ORDER_NUM;
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

    @Override
    public String toString() {
        return "Menu{" +
        "ID=" + ID +
        ", PARENT_ID=" + PARENT_ID +
        ", MENU_NAME=" + MENU_NAME +
        ", COMPONENT=" + COMPONENT +
        ", ORDER_NUM=" + ORDER_NUM +
        ", CREATE_TIME=" + CREATE_TIME +
        ", MODIFY_TIME=" + MODIFY_TIME +
        "}";
    }
}
