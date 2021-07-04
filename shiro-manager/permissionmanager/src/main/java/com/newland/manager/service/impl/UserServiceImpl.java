package com.newland.manager.service.impl;

import com.newland.manager.common.CommonConstant;
import com.newland.manager.common.Response;
import com.newland.manager.domain.User;
import com.newland.manager.mapper.UserMapper;
import com.newland.manager.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newland.manager.utils.JwtTokenUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author leellun
 * @since 2021-07-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public Response login(String username, String password) {
        try {
            User user = baseMapper.selectUser(username, password);
            if (user == null) {
                return Response.ok(CommonConstant.CODE_VALIDATE_ERROR, "用戶名或密碼錯誤");
            } else {
                Map<String, Object> map = new HashMap<>();
                map.put("token", JwtTokenUtil.createToken(username));
                return Response.ok(map, "登錄成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
