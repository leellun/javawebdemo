package com.newland.manager.mapper;

import com.newland.manager.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author leellun
 * @since 2021-07-03
 */
public interface UserMapper extends BaseMapper<User> {
    User selectUser(@Param("username") String username,@Param("password") String password);
}
