package tech.valinaa.medishop.auth.security.custom;

import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * @author Valinaa
 * @Date 2023/10/2 11:43
 * @Description 判断角色是否能够访问请求路径
 */
@Component
public class CustomAccessDecisionManager implements AuthorizationManager<Object> {
    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, Object object) {
        Collection<? extends GrantedAuthority> authorities = authentication.get().getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals("ROLE_ANONYMOUS")) {
                return new AuthorizationDecision(true);
            }
        }
        return null;
    }
}
