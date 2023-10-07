package tech.valinaa.medishop.auth.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.jose4j.json.JsonUtil;
import org.jose4j.jwa.AlgorithmConstraints;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.NumericDate;
import org.jose4j.jwt.consumer.ErrorCodes;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.lang.JoseException;
import org.springframework.security.core.userdetails.UserDetails;
import tech.valinaa.medishop.api.Result;
import tech.valinaa.medishop.auth.security.exception.AuthenticationFailedException;
import tech.valinaa.medishop.utils.json.JacksonUtil;

/**
 * @author Valinaa
 * @Date 2023/10/1 20:43
 * @Description : Jwt生成类
 */
@Slf4j
@UtilityClass
@SuppressWarnings("checkstyle:MagicNumber")
public class JwtUtil {
    
    // 生成一个RSA密钥对，用于签署和验证JWT，包装在JWK中
    private static final RsaJsonWebKey RSA_JSON_WEB_KEY = KeyPairGeneratorUtil.getInstance(AlgorithmIdentifiers.RSA_USING_SHA256);
    private static final String KEY_ID = RSA_JSON_WEB_KEY.getKeyId();
    private static final String PUBLIC_KEY_STR = RSA_JSON_WEB_KEY.toJson(RsaJsonWebKey.OutputControlLevel.PUBLIC_ONLY);
    private static final String PRIVATE_KEY_STR = RSA_JSON_WEB_KEY.toJson(RsaJsonWebKey.OutputControlLevel.INCLUDE_PRIVATE);
    private static final long ACCESS_TOKEN_EXPIRATION_TIME = 60 * 60 * 24;
    
    /**
     * jws创建token
     *
     * @param userDetails userDetails
     * @return {@link String}
     * @see String
     */
    public String createToken(UserDetails userDetails) {
        try {
            // Payload
            var claims = new JwtClaims();
            // 令牌的唯一标识符
            claims.setGeneratedJwtId();
            // 当令牌被发布/创建时（现在）
            claims.setIssuedAtToNow();
            //expire time
            var date = NumericDate.now();
            date.addSeconds(ACCESS_TOKEN_EXPIRATION_TIME);
            claims.setExpirationTime(date);
            // 在此之前，令牌无效（2分钟前）
            claims.setNotBeforeMinutesInThePast(2);
            // 令牌失效的时间长（从现在开始20分钟）
            claims.setExpirationTimeMinutesInTheFuture(20);
            // 谁创建了令牌并签署了它
            claims.setIssuer("Valinaa");
            // 主题 ,是令牌的对象
            claims.setSubject(userDetails.getUsername());
            // 令牌将被发送给谁
            claims.setAudience(String.valueOf(userDetails.getAuthorities()));
            //添加自定义参数或附加属性,必须是字符串类型
            claims.setClaim("verifyToken", Result.success());
            claims.setClaim("username", userDetails.getUsername());
            
            // JWT是一个JWS和/或一个带有JSON声明的JWE作为有效负载。
            // 在这个例子中，它是一个JWS，所以我们创建一个JsonWebSignature对象。
            var jws = new JsonWebSignature();
            // 在jw/jws上设置签名算法RS256，该算法将完整性保护声明
            jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);
            // 设置关键ID（kid）头，因为这是一种礼貌的做法。 在这个例子中，我们只有一个键但是使用键ID可以帮助 促进平稳的关键滚动过程
            jws.setKeyIdHeaderValue(KEY_ID);
            // JWS的有效负载是JWT声明的JSON内容
            jws.setPayload(claims.toJson());
            // JWT使用私钥签署
            jws.setKey(new RsaJsonWebKey(JsonUtil.parseJson(PRIVATE_KEY_STR)).getPrivateKey());
            
            /*
             * 签署JWS并生成紧凑的序列化或完整的jw/JWS 表示，它是由三个点（'.'）分隔的字符串
             * 在表单头.payload.签名中使用base64url编码的部件 如果你想对它进行加密，你可以简单地将这个jwt设置为有效负载
             * 在JsonWebEncryption对象中，并将cty（内容类型）头设置为“jwt”。
             */
            return jws.getCompactSerialization();
        } catch (JoseException e) {
            log.error("Token Create Failed with Exception!" + e.getMessage());
            throw new AuthenticationFailedException(60001, "Token Create Failed with message: " + e.getMessage());
        }
        
    }
    
    /**
     * jws校验token<br/>
     * 返回用户账号
     *
     * @param token       token
     * @param userDetails userDetails
     * @return {@link String}
     * @see String
     */
    public String verifyToken(String token, UserDetails userDetails) {
        /*
         * 使用JwtConsumer builder构建适当的JwtConsumer，它将 用于验证和处理JWT。 JWT的具体验证需求是上下文相关的
         * 但通常建议需要一个（合理的）过期时间，一个受信任的时间 发行人, 以及将你的系统定义为预期接收者的受众。
         * 如果JWT也被加密，您只需要提供一个解密密钥对构建器进行解密密钥解析器。
         */
        try {
            var consumer = new JwtConsumerBuilder()
                    // 必须有一个有效期时间
                    .setRequireExpirationTime()
                    // .setMaxFutureValidityInMinutes(5256000)
                    // 允许在验证基于时间的令牌时留有一定的余地，以计算时钟偏差。单位/秒
                    .setAllowedClockSkewInSeconds(30)
                    // 主题声明
                    .setRequireSubject()
                    // JWT需要由谁来发布,用来验证 发布人
                    .setExpectedIssuer("Valinaa")
                    .setExpectedSubject(userDetails.getUsername())
                    // JWT的目的是给谁, 用来验证观众
                    .setExpectedAudience(String.valueOf(userDetails.getAuthorities()))
                    .setEvaluationTime(NumericDate.now())
                    // 只允许在给定上下文中预期的签名算法,使用指定的算法验证
                    .setJwsAlgorithmConstraints(new AlgorithmConstraints(AlgorithmConstraints.ConstraintType.PERMIT,
                            AlgorithmIdentifiers.RSA_USING_SHA256))
                    // 用公钥验证签名 ,验证秘钥
                    .setVerificationKey(new RsaJsonWebKey(JsonUtil.parseJson(PUBLIC_KEY_STR)).getPublicKey())
                    .build();
            // 验证JWT并将其处理为jwtClaims
            var claims = consumer.processToClaims(token);
            if (claims != null) {
                log.info("认证通过！");
                var verifyInfo = JacksonUtil.toJSONString(claims.getClaimValue("verifyToken"));
                var username = String.valueOf(claims.getClaimValue("username"));
                if (log.isDebugEnabled()) {
                    log.debug("token payload携带的自定义内容:Jwt验证是否成功？ =>" + verifyInfo);
                    log.debug("token payload携带的自定义内容:携带该token的用户名 =>" + username);
                    log.debug("Jwt Succeed! The JwtClaims:" + claims);
                }
                return username;
            }
        } catch (JoseException e) {
            log.error("Public Key JSON parse error! message: " + e.getMessage());
            throw new AuthenticationFailedException(60002, "verify token error: " + e.getMessage());
        } catch (InvalidJwtException e) {
            processJwtException(e);
        }
        return null;
    }
    
    /**
     * 通过token获取用户名
     *
     * @param token token
     * @return {@link String}
     * @see String
     */
    public String getUsernameByToken(String token) {
        try {
            return new JwtConsumerBuilder()
                    .build()
                    .processToClaims(token)
                    .getSubject();
        } catch (InvalidJwtException | MalformedClaimException e) {
            processJwtException(e);
        }
        return null;
    }
    
    private void processJwtException(Exception e) {
        if (e instanceof MalformedClaimException mce) {
            log.error("Can not get Expiration Time, Audience, Subject etc. message:  " + mce.getMessage());
            throw new AuthenticationFailedException(60003, "Can not get properties of Claim. message:  " + mce.getMessage());
        }
        if (e instanceof InvalidJwtException ije) {
            log.warn("Jwt Invalid!" + e);
            try {
                if (ije.hasExpired()) {
                    log.error("JWT expired at " + ije.getJwtContext().getJwtClaims().getExpirationTime());
                }
                if (ije.hasErrorCode(ErrorCodes.AUDIENCE_INVALID)) {
                    log.error("JWT had wrong audience: " + ije.getJwtContext().getJwtClaims().getAudience());
                }
            } catch (MalformedClaimException ex) {
                processJwtException(ex);
            }
        }
    }
}
