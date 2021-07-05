package com.newland.manager.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.newland.manager.common.CommonConstant.CODE_OK;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private String message;
    private Object data;
    private int code;
    public static Response ok(Object data, String message){
        return new Response(message,data,CODE_OK);
    }
    public static Response error(int code, String message){
        return new Response(message,null,code);
    }
}
