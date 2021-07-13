package com.newland.cms.system.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.newland.cms.config.JWTToken;
import com.newland.cms.config.JWTUtil;
import com.newland.cms.domain.FebsResponse;
import com.newland.cms.domain.Menu;
import com.newland.cms.domain.Role;
import com.newland.cms.domain.User;
import com.newland.cms.domain.UserConfig;
import com.newland.cms.exception.FebsException;
import com.newland.cms.system.service.MenuService;
import com.newland.cms.system.service.RoleService;
import com.newland.cms.system.service.UserConfigService;
import com.newland.cms.system.service.UserService;
import com.newland.cms.utils.AesEncryptUtil;
import com.newland.cms.utils.DateUtil;
import com.newland.cms.utils.FebsUtil;
import com.newland.cms.utils.MD5Util;

@RestController
@CrossOrigin
public class LoginController {
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	@Autowired
	@Qualifier("roleService")
	private RoleService roleService;
	@Autowired
	@Qualifier("menuService")
	private MenuService menuService;
	@Autowired
	@Qualifier("userConfigService")
	private UserConfigService userConfigService;
	@RequestMapping("getstr")
	public String getstr() {
		return "234";
	}
	@RequestMapping("/login")
	public FebsResponse login(@RequestParam(name = "username", required = true) String username,
			@RequestParam(name = "password", required = true) String password, HttpServletRequest request)
			throws Exception {
		username = username.toLowerCase();
		try {
			password = MD5Util.encrypt(username, AesEncryptUtil.desEncrypt(password));
		} catch (Exception e) {
			e.printStackTrace();
		}

		String errorMessage = "用户名或密码错误";
		User user = userService.findByName(username);
		if (user == null || password == null) {
			throw new FebsException(errorMessage);
		}
		if (!password.equals(user.getPassword())) {
			throw new FebsException(errorMessage);
		}
		if (User.STATUS_LOCK.equals(user.getStatus())) {
			throw new FebsException("账号已被锁定,请联系管理员！");
		}

		String token = FebsUtil.encryptToken(JWTUtil.sign(username, password));
		LocalDateTime expireTime = LocalDateTime.now().plusSeconds(3600);
		String expireTimeStr = DateUtil.formatFullTime(expireTime);
		JWTToken jwtToken = new JWTToken(token, expireTimeStr);

		Map<String, Object> userInfo = this.generateUserInfo(jwtToken, user);

		return new FebsResponse().addCodeMessage(200, "认证成功", "............", userInfo);
	}

	private Map<String, Object> generateUserInfo(JWTToken token, User user) {
		String username = user.getUsername();
		Map<String, Object> userInfo = new HashMap<>();
		userInfo.put("token", token.getToken());
		userInfo.put("exipreTime", token.getExipreAt());

		List<Role> roles = roleService.findUserRole(username);
		Set<String> roleSet = roles.stream().map(Role::getRoleName).collect(Collectors.toSet());
		userInfo.put("roles", roleSet);

		List<Menu> menus = menuService.findUserPermissions(username);
		Set<String> permissions = menus.stream().map(Menu::getPerms).collect(Collectors.toSet());
		userInfo.put("permissions", permissions);

		UserConfig userConfig = this.userConfigService.findByUserId(String.valueOf(user.getUserId()));
		userInfo.put("config", userConfig);

		user.setPassword("it's a secret");
		userInfo.put("user", user);
		return userInfo;
	}
	public static void main(String[] args) {
		String password=AesEncryptUtil.encrypt("123456");
		password = MD5Util.encrypt("admin", AesEncryptUtil.desEncrypt(password));
		System.out.println(password);
	}
}
