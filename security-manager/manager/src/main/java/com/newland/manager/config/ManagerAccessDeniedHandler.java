package com.newland.manager.config;

import com.alibaba.druid.support.json.JSONUtils;
import com.newland.manager.common.Response;
import com.newland.manager.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class ManagerAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        final String message = "未认证，请在前端系统进行认证";
        response.setContentType("application/json;charset=utf-8");
        response.addHeader("Access-Control-Allow-Methods", "GET,HEAD,PUT,POST,DELETE");
        response.addHeader("Access-Control-Allow-Origin", "*");
//        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        try (PrintWriter out = response.getWriter()) {
            out.write(JsonUtils.objectToJson(Response.error(401, message)));
            out.flush();
        } catch (IOException e) {
            log.error("sendChallenge error：", e);
        }
    }

}