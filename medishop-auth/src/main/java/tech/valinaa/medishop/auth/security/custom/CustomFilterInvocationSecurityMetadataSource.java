package tech.valinaa.medishop.auth.security.custom;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;

/**
 * @author Valinaa
 * @Date 2023/10/2 11:58
 * @Description 获取访问路径所需权限
 */
@Log4j2
@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    private static final String AUTHENTICATION = "/login";
    
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        // 获取请求地址
        var requestUrl = ((FilterInvocation) object).getRequestUrl();
        log.info("请求地址: {}", requestUrl);
        // 判断是否为登录请求
        if (AUTHENTICATION.equals(requestUrl)) {
            log.info("登录请求, metadataSource放行！");
            return Collections.emptyList();
        } else {
            log.info("metadataSource 获取访问路径所需权限");
        }
        // 没有匹配上的资源，都是登录访问
        // return SecurityConfig.createList("ROLE_LOGIN");
        return Collections.emptyList();
    }
    
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return Collections.emptyList();
    }
    
    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
