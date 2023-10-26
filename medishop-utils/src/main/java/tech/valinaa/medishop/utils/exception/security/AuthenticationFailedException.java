package tech.valinaa.medishop.utils.exception.security;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Valinaa
 * @Date 2023/10/2 11:59
 * @Description 用户验证的自定义异常类
 */
@Setter
public class AuthenticationFailedException extends RuntimeException {
    @Getter
    private int code;
    private String message;
    
    /**
     * @param code    异常码
     * @param message 异常信息
     */
    public AuthenticationFailedException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
    
    @Override
    public String getMessage() {
        return message;
    }
}
