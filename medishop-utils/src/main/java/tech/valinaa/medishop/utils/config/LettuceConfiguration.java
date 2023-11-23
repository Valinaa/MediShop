package tech.valinaa.medishop.utils.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.lang.Nullable;
import tech.valinaa.medishop.utils.JacksonUtil;

import java.io.Serializable;
import java.time.Duration;
import java.util.Objects;

/**
 * @author Valinaa
 * @Date 2023/9/27 11:15
 * @Description lettuce实现的redis配置类
 */
@Configuration
@EnableCaching
@RequiredArgsConstructor
@Slf4j
public class LettuceConfiguration implements CachingConfigurer {
    
    private final LettuceConnectionFactory connectionFactory;
    
    /**
     * key 生成策略<p>
     * 不指定缓存的key时，使用keyGenerator生成Key。<p>
     * 自定义采用 类名+方法名+参数列表类型+参数值的哈希散列 作为key
     *
     * @return {@link KeyGenerator}
     * @see KeyGenerator
     */
    @Override
    @Bean
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            var sb = new StringBuilder()
                    .append(target.getClass().getName())
                    .append(method.getName())
                    .append("&");
            for (var obj : params) {
                if (obj != null) {
                    sb.append(obj.getClass().getName());
                    sb.append("&");
                    // 由于参数可能不同, hashCode肯定不一样, 缓存的key也需要不一样
                    sb.append(Objects.requireNonNull(JacksonUtil.toJSONString(obj)).hashCode());
                }
            }
            log.info("redis cache key str: " + sb);
            return sb.toString();
        };
    }
    
    /**
     * redis 模板配置
     *
     * @return {@link RedisTemplate}
     * @see RedisTemplate
     * @see Serializable
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        var template = new RedisTemplate<String, Object>();
        // 设置连接工厂，源码默认
        template.setConnectionFactory(connectionFactory);
        // string序列化
        var stringRedisSerializer = new StringRedisSerializer();
        // json序列化
        var jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(
                new ObjectMapper()
                        .setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY)
                        .activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL),
                Object.class);
        // 序列化key与value
        template.setKeySerializer(stringRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        
        return template;
    }
    
    @Bean
    @Override
    public CacheManager cacheManager() {
        return RedisCacheManager.RedisCacheManagerBuilder
                // Redis链接工厂
                .fromConnectionFactory(connectionFactory)
                // 缓存配置 通用配置  默认存储4小时
                .cacheDefaults(getCacheConfigurationWithTtl(Duration.ofHours(4)))
                // 配置同步修改或删除  put/evict
                .transactionAware()
                /*
                !对于不同的cacheName我们可以设置不同的过期时间
                .withCacheConfiguration("app:", getCacheConfigurationWithTtl(Duration.ofHours(5)))
                .withCacheConfiguration("user:", getCacheConfigurationWithTtl(Duration.ofHours(2)))
                */
                .build();
    }
    
    /**
     * 缓存的基本配置对象
     *
     * @param duration duration
     * @return {@link RedisCacheConfiguration}
     * @see RedisCacheConfiguration
     */
    private RedisCacheConfiguration getCacheConfigurationWithTtl(Duration duration) {
        return RedisCacheConfiguration
                .defaultCacheConfig()
                //! 设置key value的序列化方式
                // 设置key为String
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(
                        new StringRedisSerializer()))
                // 设置value 为自动转Json的Object
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(
                        new GenericJackson2JsonRedisSerializer()))
                //! 不缓存null
                .disableCachingNullValues()
                //! 设置缓存的过期时间
                .entryTtl(duration);
    }
    
    /**
     * 缓存的异常处理
     *
     * @return {@link CacheErrorHandler}
     * @see CacheErrorHandler
     */
    @Bean
    @Override
    public CacheErrorHandler errorHandler() {
        //! 异常处理，当Redis发生异常时，打印日志，但是程序正常走
        log.info("Initialized -> [{}]", "Redis CacheErrorHandler");
        return new CacheErrorHandler() {
            @Override
            public void handleCacheGetError(@NotNull RuntimeException e, @NotNull Cache cache, @NotNull Object key) {
                log.error("Redis occur handleCacheGetError：key -> [{}]", key, e);
            }
            
            @Override
            public void handleCachePutError(RuntimeException e, Cache cache,
                                            Object key, @Nullable Object value) {
                log.error("Redis occur handleCachePutError：key -> [{}]；value -> [{}]", key, value, e);
            }
            
            @Override
            public void handleCacheEvictError(RuntimeException e, Cache cache, Object key) {
                log.error("Redis occur handleCacheEvictError：key -> [{}]", key, e);
            }
            
            @Override
            public void handleCacheClearError(RuntimeException e, Cache cache) {
                log.error("Redis occur handleCacheClearError：", e);
            }
        };
    }
    
}
