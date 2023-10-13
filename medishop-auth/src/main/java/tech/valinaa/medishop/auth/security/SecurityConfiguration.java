package tech.valinaa.medishop.auth.security;

import lombok.RequiredArgsConstructor;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.lang.JoseException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import tech.valinaa.medishop.auth.security.custom.CustomAuthenticationHandler;
import tech.valinaa.medishop.auth.security.custom.CustomAuthorizationTokenFilter;
import tech.valinaa.medishop.auth.user.UserService;
import tech.valinaa.medishop.utils.Constants;

import java.util.ArrayList;

/**
 * @author Valinaa
 * @Date : 2023/10/2 11:39
 * @Description : Spring Security 配置类
 */

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    
    private final UserService userService;
    
    private final CustomAuthenticationHandler customAuthenticationHandler;
    
    private final CustomAuthorizationTokenFilter customAuthorizationTokenFilter;
    
    /**
     * 密码加密器
     *
     * @return {@link PasswordEncoder}
     * @see PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    /**
     * 跨域配置
     *
     * @return {@link CorsConfigurationSource}
     * @see CorsConfigurationSource
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        // 允许跨域访问的 URL
        var allowedOriginsUrl = new ArrayList<String>();
        allowedOriginsUrl.add("*");
        var config = new CorsConfiguration();
        config.setAllowCredentials(true);
        // 设置允许跨域访问的 URL
        config.setAllowedOrigins(allowedOriginsUrl);
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
    
    /**
     * 防火墙
     *
     * @return {@link StrictHttpFirewall}
     * @see StrictHttpFirewall
     */
    @Bean
    public StrictHttpFirewall httpFirewall() {
        var firewall = new StrictHttpFirewall();
        firewall.setAllowedHeaderNames((header) -> true);
        firewall.setAllowedHeaderValues((header) -> true);
        firewall.setAllowedParameterNames((parameter) -> true);
        return firewall;
    }
    
    /**
     * 配置防火墙
     *
     * @return {@link WebSecurityCustomizer}
     */
    @Bean
    public WebSecurityCustomizer securityCustomizer() {
        return web -> web.httpFirewall(this.httpFirewall());
    }
    
    /**
     * 配置过滤器链
     *
     * @param http {@link HttpSecurity}
     * @return {@link SecurityFilterChain}
     * @throws Exception 异常
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement((sessionManagement) -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationManager(this.authenticationManager())
                .addFilterBefore(customAuthorizationTokenFilter,
                        UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling((exceptionHandling) -> exceptionHandling
                        .authenticationEntryPoint(customAuthenticationHandler)
                        .accessDeniedHandler(customAuthenticationHandler))
                .formLogin(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorizeRequests) -> authorizeRequests
                        // 允许所有OPTIONS请求
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        // 允许直接访问授权登录、注册接口、MVC 默认错误地址
                        .requestMatchers("/api/v1/login", "/api/v1/register", "/api/v1/logout",
                                "/error", "/doc.html", "/swagger**/**", "/webjars/**",
                                "/v3/api-docs/**", "/favicon.ico")
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                )
                .cors((cors) -> cors.configurationSource(this.corsConfigurationSource()))
                .logout((logout) -> logout.logoutUrl("/logout")
                        .logoutSuccessUrl("/index")
                        .logoutSuccessHandler(customAuthenticationHandler)
                        .permitAll());
        return http.build();
    }
    
    /**
     * 配置认证管理器
     *
     * @return {@link AuthenticationManager}
     */
    @Bean
    public AuthenticationManager authenticationManager() {
        var daoAuthenticationProvider = new DaoAuthenticationProvider(this.passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userService);
        return new ProviderManager(daoAuthenticationProvider);
    }
    
    /**
     * 公钥密钥生成
     *
     * @return {@link RsaJsonWebKey}
     * @see RsaJsonWebKey
     */
    @Bean
    @SuppressWarnings("checkstyle:MagicNumber")
    public RsaJsonWebKey rsaJsonWebKey() {
        // 生成一个RSA密钥对，用于签署和验证JWT，包装在JWK中
        try {
            var rsaJsonWebKey = RsaJwkGenerator.generateJwk(2048);
            rsaJsonWebKey.setKeyId(Constants.KEY_ID);
            rsaJsonWebKey.setAlgorithm(AlgorithmIdentifiers.RSA_USING_SHA256);
            return rsaJsonWebKey;
        } catch (JoseException e) {
            throw new RuntimeException("Failed to generate jwk, message: " + e.getMessage());
        }
    }
}
