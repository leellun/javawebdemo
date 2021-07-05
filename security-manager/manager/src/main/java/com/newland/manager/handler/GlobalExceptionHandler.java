package com.newland.manager.handler;

import com.newland.manager.common.Response;
import lombok.extern.log4j.Log4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
@Log4j
public class GlobalExceptionHandler {
    @ExceptionHandler({RuntimeException.class, Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response handleException(Exception e) {
        log.error("系统内部异常，异常信息：", e);
        String message = "系统内部异常，异常信息";
        return Response.error(500, message);
    }
}
