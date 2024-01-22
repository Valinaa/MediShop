package tech.valinaa.medishop.neo4j.config;

import org.neo4j.cypherdsl.core.renderer.Configuration;
import org.neo4j.cypherdsl.core.renderer.Dialect;
import org.springframework.context.annotation.Bean;

/**
 * @author Valinaa
 * @Date 2024/1/17 16:06
 * @Description Neo4j 配置类
 */
@org.springframework.context.annotation.Configuration
public class Neo4jConfiguration {
    @Bean
    Configuration cypherDslConfiguration() {
        return Configuration.newConfig()
                .withDialect(Dialect.NEO4J_5).build();
    }
}
