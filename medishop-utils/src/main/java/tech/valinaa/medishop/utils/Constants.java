package tech.valinaa.medishop.utils;

import lombok.experimental.UtilityClass;

import java.util.UUID;
import java.util.regex.Pattern;

/**
 * @author Valinaa
 * @Date 2023/10/12 10:38
 * @Description 常量类
 */

@UtilityClass
public class Constants {
    
    /**
     * HttpServletResponse 响应头相关
     */
    public static final String CONTENT_TYPE = "application/json";
    public static final String CHARACTER_ENCODING = "UTF-8";
    public static final String KEY_ID = UUID.randomUUID().toString().replaceAll("-", "");
    
    /**
     * JWT存储的请求头相关
     */
    public static final String AUTH_HEADER = "Authorization";
    // access_token 过期时间 3小时
    public static final long ACCESS_TOKEN_EXPIRATION_TIME = 60 * 60 * 3;
    // refresh_token 过期时间 14天
    public static final long REFRESH_TOKEN_EXPIRATION_TIME = 60 * 60 * 24 * 14;
    // 时间日期格式, 类似于 "2021-10-12 10:38:00.000+08:00"
    public static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss.SSSXXX";
    
    /**
     * 其它相关Constant
     */
    
    // IPV4地址正则表达式
    public static final Pattern IPV4_PATTERN = Pattern.compile("^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\."
            + "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\."
            + "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\."
            + "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");
    /**
     * 验证码相关
     */
    
    // 验证码字符数
    public static final int CAPTCHA_CHAR_COUNT = 5;
    // 验证码宽度
    public static final int CAPTCHA_WIDTH = 200;
    // 验证码高度
    public static final int CAPTCHA_HEIGHT = 80;
}
