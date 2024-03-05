package tech.valinaa.medishop.utils.translation;

import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.DigestUtils;
import tech.valinaa.medishop.utils.Constants;
import tech.valinaa.medishop.utils.JacksonUtil;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;

/**
 * @author Valinaa
 * @Date 2024/3/5 下午1:47
 * @Description 百度翻译开放平台接口调用
 */

@Log4j2
@UtilityClass
public class BaiDuTranslationUtil {
    @Value("${baidu.translation.appId}")
    private String appID;
    @Value("${baidu.translation.secretKey}")
    private String secretKey;
    
    /**
     * 领域翻译通用方法
     *
     * @param query  待翻译文本
     * @param from   源语言
     * @param to     目标语言
     * @param domain 领域
     * @return 翻译结果
     * @throws IOException          IO异常
     * @throws InterruptedException 线程中断异常
     */
    public String fieldTranslate(String query, String from, String to, String domain) throws IOException, InterruptedException {
        var salt = String.valueOf(System.currentTimeMillis());
        var sign = DigestUtils.md5DigestAsHex((appID + query + salt + secretKey).getBytes());
        var fieldTransUrl = Constants.BAIDU_FIELD_TRANSLATION_URL
                + "?q=" + query
                + "&from=" + from
                + "&to=" + to
                + "&appid=" + appID
                + "&salt=" + salt
                + "&domain=" + domain
                + "&sign=" + sign;
        var httpClient = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(URI.create(fieldTransUrl)).GET().build();
        var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString()).body();
        return Objects.requireNonNull(JacksonUtil.parseJSONObject(response))
                .path("trans_result")
                .get(0)
                .path("dst")
                .asText();
    }
    
    /**
     * 通用翻译方法
     *
     * @param query 待翻译文本
     * @return 翻译结果
     */
    public String medicalTranslate(String query) {
        try {
            return fieldTranslate(query, "en", "zh", "medicine");
        } catch (IOException e) {
            log.error("IOException when using BaiDu Field Translation! ", e);
            return null;
        } catch (InterruptedException e) {
            log.error("InterruptedException when using BaiDu Field Translation! ", e);
            Thread.currentThread().interrupt();
            return null;
        }
    }
}
