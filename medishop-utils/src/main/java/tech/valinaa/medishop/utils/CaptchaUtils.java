package tech.valinaa.medishop.utils;

import cn.hutool.captcha.AbstractCaptcha;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.generator.RandomGenerator;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Valinaa
 * @Date 2023/11/15 16:40
 * @Description 验证码工具类
 */
@Slf4j
@UtilityClass
public class CaptchaUtils {
    
    
    @SuppressWarnings("checkstyle:MagicNumber")
    private static AbstractCaptcha randomCaptchaType(Long timestamp) {
        return switch ((int) (timestamp % 4)) {
            case 0 -> CaptchaUtil.createLineCaptcha(
                    Constants.CAPTCHA_WIDTH, Constants.CAPTCHA_HEIGHT, Constants.CAPTCHA_CHAR_COUNT, 10);
            case 1 -> CaptchaUtil.createCircleCaptcha(
                    Constants.CAPTCHA_WIDTH, Constants.CAPTCHA_HEIGHT, Constants.CAPTCHA_CHAR_COUNT, 10);
            case 2 -> CaptchaUtil.createShearCaptcha(
                    Constants.CAPTCHA_WIDTH, Constants.CAPTCHA_HEIGHT, Constants.CAPTCHA_CHAR_COUNT, 8);
            default -> CaptchaUtil.createGifCaptcha(
                    Constants.CAPTCHA_WIDTH, Constants.CAPTCHA_HEIGHT, Constants.CAPTCHA_CHAR_COUNT);
        };
    }
    
    /**
     * 随机生成验证码
     *
     * @param timestamp 时间戳
     * @return 验证码
     */
    public static AbstractCaptcha randomCaptcha(Long timestamp) {
        var captcha = randomCaptchaType(timestamp);
        captcha.setGenerator(new RandomGenerator(Constants.CAPTCHA_CHAR_COUNT));
        if (log.isDebugEnabled()) {
            log.debug("生成的验证码为：{}", captcha.getCode());
        }
        return captcha;
    }
}
