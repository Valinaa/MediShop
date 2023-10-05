package tech.valinaa.medishop.bootstrap;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Valinaa
 * @Date 2023/9/26 13:54
 * @Description MediShop启动类
 */
@MapperScan({"tech.valinaa.medishop.core.dao", "tech.valinaa.medishop.auth.security.user.web"})
@SuppressWarnings({"checkstyle:HideUtilityClassConstructor", "checkstyle:MissingJavadocMethod"})
@SpringBootApplication(scanBasePackages = "tech.valinaa.medishop")
public class MediShopApplication {
    public static void main(String[] args) {
        SpringApplication.run(MediShopApplication.class);
    }
}
