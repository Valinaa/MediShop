package tech.valinaa.medishop.bootstrap;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Valinaa
 * @Date 2023/9/26 13:54
 * @Description MediShop启动类
 */
@MapperScan({"tech.valinaa.medishop.core.dao"})
@SpringBootApplication(scanBasePackages = "tech.valinaa.medishop")
public class MediShopApplication {
    public static void main(String[] args) {
        System.setProperty("org.springframework.boot.logging.LoggingSystem",
                "org.springframework.boot.logging.log4j2.Log4J2LoggingSystem");
        SpringApplication.run(MediShopApplication.class);
    }
}
