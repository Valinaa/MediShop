package tech.valinaa.medishop.utils.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Valinaa
 * @Date 2023/9/27 13:53
 * @Description Swagger配置类
 */
@Configuration
public class SwaggerConfiguration {
        @Bean
        public GroupedOpenApi publicApi() {
            return GroupedOpenApi.builder()
                    .group("medishop")
                    .packagesToScan("tech.valinaa.medishop.web.controller")
                    .packagesToScan("tech.valinaa.medishop.core.model.dataobject")
                    .pathsToMatch("/**")
                    .build();
        }
        @Bean
        public OpenAPI api() {
            return new OpenAPI().info(new Info().title("文档标题")
                    .description("文档描述")
                    .version("v1.0.0")
            );
        }
}
