package com.newland.cms.utils;

import java.util.stream.IntStream;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;

/**
 * FEBS工具类
 */
public class FebsUtil {
    // token缓存前缀
    public static final String TOKEN_CACHE_PREFIX = "febs.cache.token.";
    /**
     * token 加密
     *
     * @param token token
     * @return 加密后的 token
     */
    public static String encryptToken(String token) {
        try {
            EncryptUtil encryptUtil = new EncryptUtil(TOKEN_CACHE_PREFIX);
            return encryptUtil.encrypt(token);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * token 解密
     *
     * @param encryptToken 加密后的 token
     * @return 解密后的 token
     */
    public static String decryptToken(String encryptToken) {
        try {
            EncryptUtil encryptUtil = new EncryptUtil(TOKEN_CACHE_PREFIX);
            return encryptUtil.decrypt(encryptToken);
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * 驼峰转下划线
     *
     * @param value 待转换值
     * @return 结果
     */
    public static String camelToUnderscore(String value) {
        if (StringUtils.isBlank(value))
            return value;
        String[] arr = org.apache.commons.lang3.StringUtils.splitByCharacterTypeCamelCase(value);
        if (arr.length == 0)
            return value;
        StringBuilder result = new StringBuilder();
        IntStream.range(0, arr.length).forEach(i -> {
            if (i != arr.length - 1)
                result.append(arr[i]).append(StringPool.UNDERSCORE);
            else
                result.append(arr[i]);
        });
        return result.toString().toLowerCase();
    }
}
