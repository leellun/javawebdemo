package com.newland.manager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newland.manager.common.CommonConstant;
import com.newland.manager.common.Response;
import com.newland.manager.domain.InfoVO;
import com.newland.manager.domain.Role;
import com.newland.manager.domain.User;
import com.newland.manager.manager.UserManager;
import com.newland.manager.mapper.MenuMapper;
import com.newland.manager.mapper.RoleMapper;
import com.newland.manager.mapper.UserMapper;
import com.newland.manager.service.IUserService;
import com.newland.manager.utils.JwtTokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private UserManager userManager;

    @Override
    public Response login(String username, String password) {
        User user = baseMapper.selectUser(username);
        if (user == null) {
            return Response.ok(CommonConstant.CODE_VALIDATE_ERROR, "用戶不存在");
        } else if (StringUtils.equals(user.getPASSWORD(), password)) {
            return Response.ok(CommonConstant.CODE_VALIDATE_ERROR, "密码错误");
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("token", JwtTokenUtil.createToken(username));
            return Response.ok(map, "登錄成功");
        }
    }

    @Override
    public User getUser(String username) {
        return baseMapper.selectUser(username);
    }

    @Override
    public Response info(String usernmae) {
        User user = userManager.getUser(usernmae);
        List<Role> roleList = roleMapper.selectRoleList(user.getID());
        List<Long> roleIds = roleList.stream().mapToLong(Role::getId).boxed().collect(Collectors.toList());
        List<String> roles = roleList.stream().map(Role::getRole_name).collect(Collectors.toList());
        List<String> menus = menuMapper.selectMenus(roleIds);
        InfoVO infoVO = new InfoVO();
        infoVO.setAvatar(user.getAvatar());
        infoVO.setName(user.getUSERNAME());
        infoVO.setRoles(roles);
        infoVO.setData(menus);
        return Response.ok(infoVO, "");
    }

    @Override
    public Response list(String username) {
        return Response.ok(baseMapper.selectUsersWithRoles(), "");
    }
}
