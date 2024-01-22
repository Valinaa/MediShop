package tech.valinaa.medishop.bootstrap;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

/**
 * @author Valinaa
 * @Date 2023/9/26 13:54
 * @Description MediShop启动类
 */
@MapperScan({"tech.valinaa.medishop.core.dao"})
@EnableNeo4jRepositories({"tech.valinaa.medishop.neo4j.dao"})
@SpringBootApplication(scanBasePackages = "tech.valinaa.medishop")
public class MediShopApplication {
    public static void main(String[] args) {
        SpringApplication.run(MediShopApplication.class);
    }
}
