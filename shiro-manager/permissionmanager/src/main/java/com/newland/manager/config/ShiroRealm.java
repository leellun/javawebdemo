package com.newland.manager.config;

import com.newland.manager.domain.User;
import com.newland.manager.manager.UserManager;
import com.newland.manager.utils.JwtTokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    private UserManager userManager;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
        String username = JwtTokenUtil.getUserNmae(token.toString());
        if(JwtTokenUtil.isExpire(token)){
            throw new RuntimeException("token已經過期了");
        }
        if(StringUtils.isEmpty(username)){
            throw new RuntimeException("token校驗不通過");
        }
        User user=userManager.getUser(username);
        if(user==null){
            throw new RuntimeException("请重新登录");
        }
        return new SimpleAuthenticationInfo(token, token, username);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection token) {
        String username = JwtTokenUtil.getUserNmae(token.toString());

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        // 获取用户角色集
        Set<String> roleSet = userManager.getRoles(username);
        simpleAuthorizationInfo.setRoles(roleSet);

        // 获取用户权限集
        Set<String> permissionSet = userManager.getPermissions(username);
        simpleAuthorizationInfo.setStringPermissions(permissionSet);
        return simpleAuthorizationInfo;
    }
}
