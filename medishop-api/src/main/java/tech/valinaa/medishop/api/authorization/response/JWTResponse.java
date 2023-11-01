package tech.valinaa.medishop.api.authorization.response;

import lombok.Data;

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
    private String tokenType = "Bearer";
    // 过期时间，单位秒
    private Long expiredIn;
}
