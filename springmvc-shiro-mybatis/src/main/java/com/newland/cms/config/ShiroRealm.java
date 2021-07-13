package com.newland.cms.config;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.StringUtils;

import com.newland.cms.domain.Menu;
import com.newland.cms.domain.Role;
import com.newland.cms.domain.User;
import com.newland.cms.system.service.MenuService;
import com.newland.cms.system.service.RoleService;
import com.newland.cms.system.service.UserService;

public class ShiroRealm extends AuthorizingRealm{
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	@Autowired
	@Qualifier("roleService")
	private RoleService roleService;
	@Autowired
	@Qualifier("menuService")
	private MenuService menuService;
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username=JWTUtil.getUsername(principals.toString());
		SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
		List<Role> roles=roleService.findUserRole(username);
		Set<String> roleSet = roles.stream().map(Role::getRoleName).collect(Collectors.toSet());
		simpleAuthorizationInfo.setRoles(roleSet);
		List<Menu> menus=menuService.findUserPermissions(username);
		Set<String> permissions = menus.stream().map(Menu::getPerms).collect(Collectors.toSet());
		simpleAuthorizationInfo.setStringPermissions(permissions);

		return simpleAuthorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		String token=(String) authenticationToken.getCredentials();
		String username = JWTUtil.getUsername(token);

        if (StringUtils.isEmpty(username))
            throw new AuthenticationException("token校验不通过");
        User user=userService.findByName(username);
        if (user == null)
            throw new AuthenticationException("用户名或密码错误");
        if (!JWTUtil.verify(token, username, user.getPassword()))
            throw new AuthenticationException("token校验不通过");
        return new SimpleAuthenticationInfo(token, token, getName());
	}

}
