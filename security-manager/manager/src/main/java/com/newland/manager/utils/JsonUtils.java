package com.newland.manager.utils;

import com.alibaba.fastjson.JSON;

public class JsonUtils {
    public static String objectToJson(Object object) {
        return JSON.toJSONString(object);
    }
}
