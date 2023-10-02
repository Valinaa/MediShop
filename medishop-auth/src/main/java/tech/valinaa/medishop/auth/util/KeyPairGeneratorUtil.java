package tech.valinaa.medishop.auth.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.lang.JoseException;

import java.util.UUID;

/**
 * @author Valinaa
 * @Date 2023/10/1 20:57
 * @Description 生成 keyId与公钥秘钥
 */
@Slf4j
@UtilityClass
@SuppressWarnings("checkstyle:MagicNumber")
public class KeyPairGeneratorUtil {
    
    private static RsaJsonWebKey rsaJsonWebKey;
    private static final String KEY_ID = UUID.randomUUID().toString().replaceAll("-", "");
    
    /**
     * 创建jwk KEY_ID , 公钥 ，秘钥
     */
    public void createKeyPair() {
        RsaJsonWebKey jwk = null;
        try {
            jwk = RsaJwkGenerator.generateJwk(2048);
        } catch (JoseException e) {
            log.error("Failed to generate jwk, message: " + e.getMessage());
        }
        assert jwk != null;
        jwk.setKeyId(KEY_ID);
        //采用的签名算法 RS256
        jwk.setAlgorithm(AlgorithmIdentifiers.RSA_USING_SHA256);
        var publicKey = jwk.toJson(RsaJsonWebKey.OutputControlLevel.PUBLIC_ONLY);
        var privateKey = jwk.toJson(RsaJsonWebKey.OutputControlLevel.INCLUDE_PRIVATE);
        log.info("KEY_ID= {} \n", KEY_ID);
        log.info("公钥 publicKeyStr= {} \n", publicKey);
        log.info("私钥 privateKeyStr= {}", privateKey);
    }
    
    
    /**
     * 公钥密钥生成方法2
     *
     * @param algorithm 加密算法
     * @return {@link RsaJsonWebKey}
     * @see RsaJsonWebKey
     */
    public RsaJsonWebKey getInstance(String algorithm) {
        // 生成一个RSA密钥对，用于签署和验证JWT，包装在JWK中
        if (rsaJsonWebKey == null) {
            try {
                rsaJsonWebKey = RsaJwkGenerator.generateJwk(2048);
                rsaJsonWebKey.setKeyId(KEY_ID);
                rsaJsonWebKey.setAlgorithm(algorithm);
            } catch (JoseException e) {
                log.error("Failed to generate jwk, message: " + e.getMessage());
            }
        }
        // 给JWK一个关键ID（kid），这是礼貌的做法
        return rsaJsonWebKey;
    }
}
