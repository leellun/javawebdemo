package com.newland.manager.config;

import com.alibaba.druid.support.json.JSONUtils;
import com.newland.manager.common.Response;
import com.newland.manager.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class ManagerLoginUrlAuthenticationEntryPoint implements AuthenticationEntryPoint {
    public ManagerLoginUrlAuthenticationEntryPoint() {
    }

    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        response.addHeader("Access-Control-Allow-Methods", "GET,HEAD,PUT,POST,DELETE");
        response.addHeader("Access-Control-Allow-Origin", "*");
        PrintWriter out = response.getWriter();
        final String message = "未登陆!";
        out.write(JsonUtils.objectToJson(Response.error(401, message)));
        out.flush();
        out.close();
    }

}
