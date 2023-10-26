package tech.valinaa.medishop.api.authorization.response;

import lombok.Data;
import tech.valinaa.medishop.core.Constants;

import java.time.LocalDateTime;

/**
 * @author Valinaa
 * @Date 2023/10/13 17:23
 * @Description JWT返回实体
 */
@Data
public class JWTResponse {
    // 授权token
    private String accessToken;
    // 刷新token
    private String refreshToken;
    // token类型,默认Bearer
    private String tokenType = Constants.DEFAULT_TOKEN_HEAD;
    // 过期时间
    private LocalDateTime expiresIn;
}
