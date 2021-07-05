package com.newland.manager.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

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
@ToString
@Data
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
    private String avatar;

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
}
