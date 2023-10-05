package tech.valinaa.medishop.utils.exception;

import lombok.Getter;
import lombok.Setter;

import java.text.MessageFormat;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Valinaa
 * @Date 2023/9/27 11:58
 * @Description RedisUtil 异常类
 */
@Setter
@SuppressWarnings({"checkstyle:MissingJavadocMethod", "unused"})
public class RedisOperationException extends RuntimeException {
    
    private String errorCode;
    
    @Getter
    private Object[] params;
    
    public RedisOperationException(String message) {
        super(message);
        this.errorCode = this.getErrorCode();
    }
    
    public RedisOperationException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
    
    public RedisOperationException(Object[] params, String message) {
        super(MessageFormat.format(message, params));
        this.errorCode = this.getErrorCode();
        this.params = params;
    }
    
    public RedisOperationException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = this.getErrorCode();
    }
    
    public RedisOperationException(String errorCode, Object[] params, String message) {
        super(MessageFormat.format(message, params));
        this.errorCode = errorCode;
        this.params = params;
    }
    
    public RedisOperationException(Object[] params, String message, Throwable cause) {
        super(MessageFormat.format(message, params), cause);
        this.errorCode = this.getErrorCode();
        this.params = params;
    }
    
    public RedisOperationException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }
    
    public RedisOperationException(String errorCode, Object[] params, String message, Throwable cause) {
        super(MessageFormat.format(message, params), cause);
        this.errorCode = errorCode;
        this.params = params;
    }
    
    public String getErrorCode() {
        if (this.errorCode.isBlank()) {
            this.errorCode = (Stream.of(this.getClass().getSimpleName()
                            .split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])"))
                    .map(String::toUpperCase).collect(Collectors.joining("_")))
                    .replace("_EXCEPTION", "");
        }
        return this.errorCode;
    }
}
