package tech.valinaa.medishop.api.translation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Valinaa
 * @Date 2024/3/5 下午2:48
 * @Description 百度翻译Api
 */

@Tag(name = "百度翻译Api", description = "百度翻译相关Api")
@RequestMapping("/api/v1/translation")
public interface BaiDuTranslationApi {
    
    /**
     * 生物医药领域翻译
     *
     * @param query 待翻译文本
     * @return 翻译结果
     */
    @Operation(summary = "生物医药领域翻译", description = "生物医药领域翻译")
    @GetMapping("/medical")
    String medicalTranslate(@RequestParam String query);
}
