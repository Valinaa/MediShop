package tech.valinaa.medishop.utils.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Valinaa
 * @Date 2023/9/27 13:53
 * @Description Swagger配置类
 */
@Configuration
@SuppressWarnings("checkstyle:MissingJavadocMethod")
public class SwaggerConfiguration {
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("medishop")
                .packagesToScan("tech.valinaa.medishop")
                .pathsToMatch("/**")
                .build();
    }
    
    @Bean
    public OpenAPI api() {
        return new OpenAPI().info(
                new Info()
                        .title("MediShop API Document")
                        .summary("MediShop API Document")
                        .description("Microservice Platform API Document")
                        .version("v1.0.0")
                        .contact(new Contact().name("Valinaa").email("1114854003@qq.com").url("https://www.valinaa.tech"))
                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))
        );
    }
}
