package tech.valinaa.medishop.auth.security.custom;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.lang.JoseException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import tech.valinaa.medishop.auth.security.exception.AuthenticationFailedException;
import tech.valinaa.medishop.auth.util.JwtUtil;

import java.io.IOException;
import java.util.Optional;

/**
 * @author Valinaa
 * @Date 2023/10/2 11:53
 * @Description 自定义验证token
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CustomAuthorizationTokenFilter extends OncePerRequestFilter {
    
    private final UserDetailsService userDetailsService;
    /**
     * JWT存储的请求头
     */
    private static final String TOKEN_HEADER = "Authorization";
    
    @SuppressWarnings({"checkstyle:MagicNumber", "checkstyle:ReturnCount"})
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {
        // 获取Header
        final String authHeader = request.getHeader(TOKEN_HEADER);
        // 存在token但不是tokenHead开头
        if (Optional.ofNullable(authHeader).isEmpty() || !authHeader.startsWith("BEARER ")) {
            log.warn("authHeader is null come from TokenFilter");
            filterChain.doFilter(request, response);
            return;
        }
        // 字段截取authToken
        var authToken = authHeader.substring(7);
        
        // 没有token,直接放行
        if (authToken.isBlank()) {
            log.warn("authToken is null come from TokenFilter");
            filterChain.doFilter(request, response);
            return;
        }
        // 根据authToken获取username
        String username;
        try {
            username = JwtUtil.getUsernameByToken(authToken);
        } catch (InvalidJwtException | MalformedClaimException e) {
            throw new AuthenticationFailedException(60001, "get username by token error: " + e.getMessage());
        }
        // token存在用户名但未登录
        if (null != username && null == SecurityContextHolder.getContext().getAuthentication()) {
            // 登录
            var userDetails = userDetailsService.loadUserByUsername(username);
            // 验证token是否有效，如果有效，将他重新放到用户对象里。
            // TODO 此处token有效性可以从redis｜数据库中获取
//            Boolean isTokenValid = true;
            try {
                Optional.ofNullable(JwtUtil.verifyToken(authToken, userDetails))
                        .ifPresent(t -> {
                            if (log.isDebugEnabled()) {
                                log.debug("Token is valid, username: {}", t);
                            }
                            var authentication = new UsernamePasswordAuthenticationToken(
                                    userDetails, null, userDetails.getAuthorities());
                            // 重新设置到用户对象里
                            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                            SecurityContextHolder.getContext().setAuthentication(authentication);
                        });
            } catch (MalformedClaimException | JoseException e) {
                throw new AuthenticationFailedException(60002, "verify token error: " + e.getMessage());
            }
        }
        // 放行
        filterChain.doFilter(request, response);
    }
}
