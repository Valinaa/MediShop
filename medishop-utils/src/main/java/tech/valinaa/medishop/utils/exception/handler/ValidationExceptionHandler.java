package tech.valinaa.medishop.utils.exception.handler;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tech.valinaa.medishop.core.model.Result;

import java.util.Optional;

/**
 * @author Valinaa
 * @Date 2023/10/10 16:17
 * @Description 检验异常处理器
 */
@RestControllerAdvice
public class ValidationExceptionHandler {
    /**
     * 校验错误拦截处理
     *
     * @param exception {@link MethodArgumentNotValidException}
     * @return 错误信息
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<String> validationBodyException(MethodArgumentNotValidException exception) {
        var fieldError =
                Optional.ofNullable(exception.getBindingResult().getFieldError())
                        .orElseGet(() -> new FieldError("unknown object",
                                "unknown field", "Unknown validation error"));
        var msg = fieldError.getField() + "\t" + fieldError.getDefaultMessage();
        return Result.build(null, 400, msg);
    }
}
