package com.newland.manager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.newland.manager.domain.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author leellun
 * @since 2021-07-03
 */
public interface MenuMapper extends BaseMapper<Menu> {
    List<String> selectMenus(@Param("roleIds") List<Long> roleIds);
}
