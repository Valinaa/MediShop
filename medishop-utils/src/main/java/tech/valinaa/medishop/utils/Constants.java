package tech.valinaa.medishop.utils;

import lombok.experimental.UtilityClass;

import java.util.UUID;

/**
 * @author Valinaa
 * @Date 2023/10/12 10:38
 * @Description 常量类
 */

@UtilityClass
public class Constants {
    /**
     * HttpServletResponse响应头
     */
    public static final String CONTENT_TYPE = "application/json";
    public static final String CHARACTER_ENCODING = "UTF-8";
    public static final String KEY_ID = UUID.randomUUID().toString().replaceAll("-", "");
    /**
     * JWT存储的请求头
     */
    public static final String AUTH_HEADER = "Authorization";
    public static final String DEFAULT_TOKEN_HEAD = "Bearer";
    // 时间日期格式
    public static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";
}
