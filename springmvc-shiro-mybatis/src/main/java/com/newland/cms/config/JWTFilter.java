package com.newland.cms.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.util.AntPathMatcher;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.newland.cms.domain.FebsResponse;
import com.newland.cms.utils.FebsUtil;

public class JWTFilter extends BasicHttpAuthenticationFilter {
	private static final String TOKEN = "Authentication";
	private AntPathMatcher pathMatcher = new AntPathMatcher();

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		HttpServletRequest httpServletRequest=(HttpServletRequest) request;
		if (pathMatcher.match("/getstr", httpServletRequest.getRequestURI())) {
			return true;
		}
		return super.isAccessAllowed(request, response, mappedValue);
	}

	@Override
	protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
		HttpServletRequest req = (HttpServletRequest) request;
		String token = req.getHeader(TOKEN);
		return token != null;
	}

	@Override
	protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String token = httpServletRequest.getHeader(TOKEN);
		JWTToken jwtToken = new JWTToken(FebsUtil.decryptToken(token));
		try {
			getSubject(request, response).login(jwtToken);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 对跨域提供支持
	 */
	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
		System.out.println("=================>"+System.currentTimeMillis());
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
		httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
		httpServletResponse.setHeader("Access-Control-Allow-Headers",
				httpServletRequest.getHeader("Access-Control-Request-Headers"));
		// 跨域时会首先发送一个 option请求，这里我们给 option请求直接返回正常状态
		if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
			httpServletResponse.setStatus(HttpStatus.OK.value());
			return false;
		}
		return super.preHandle(request, response);
	}

	/**
	 * 是否修改了默认401返回
	 */
	@Override
	protected boolean sendChallenge(ServletRequest request, ServletResponse response) {
		HttpServletResponse httpResponse = WebUtils.toHttp(response);
		httpResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
		httpResponse.setCharacterEncoding("utf-8");
		httpResponse.setContentType("application/json; charset=utf-8");
		final String message = "未认证，请在前端系统进行认证";
		try (PrintWriter out = httpResponse.getWriter()) {
			FebsResponse tandemResponse = new FebsResponse().message(message).code("401").status("error");
			String json = JSON.toJSONString(tandemResponse);
			out.print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
