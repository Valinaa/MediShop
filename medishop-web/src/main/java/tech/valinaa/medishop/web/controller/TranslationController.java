package tech.valinaa.medishop.web.controller;

import org.springframework.web.bind.annotation.RestController;
import tech.valinaa.medishop.api.translation.BaiDuTranslationApi;
import tech.valinaa.medishop.utils.translation.BaiDuTranslationUtil;

/**
 * @author Valinaa
 * @Date 2024/3/5 下午2:58
 */
@RestController
public class TranslationController implements BaiDuTranslationApi {
    
    /**
     * unicode 转中文
     *
     * @param unicode Unicode字符
     * @return 转换后的中文
     */
    @SuppressWarnings("checkstyle:MagicNumber")
    private static String unicodeDecode(String unicode) {
        if (!unicode.contains("\\u")) {
            return unicode;
        }
        var result = new StringBuilder();
        var hexArray = unicode.split("\\\\u");
        for (var hex : hexArray) {
            if (!hex.isEmpty()) {
                var code = Integer.parseInt(hex, 16);
                result.append((char) code);
            }
        }
        return result.toString();
    }
    
    @Override
    public String medicalTranslate(String query) {
        var result = BaiDuTranslationUtil.medicalTranslate(query);
        if (result == null) {
            return "翻译失败";
        }
        return unicodeDecode(result);
    }
}
