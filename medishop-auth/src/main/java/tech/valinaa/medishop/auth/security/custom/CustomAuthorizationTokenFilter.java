package tech.valinaa.medishop.auth.security.custom;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import tech.valinaa.medishop.api.Result;
import tech.valinaa.medishop.api.enums.ResultCodeEnum;
import tech.valinaa.medishop.utils.Constants;
import tech.valinaa.medishop.utils.JacksonUtil;
import tech.valinaa.medishop.utils.JwtUtil;

import java.io.IOException;
import java.util.Objects;

/**
 * @author Valinaa
 * @Date 2023/10/2 11:53
 * @Description 自定义验证token
 */
@Log4j2
@Component
public class CustomAuthorizationTokenFilter extends OncePerRequestFilter {
    
    @Override
    @SuppressWarnings("checkstyle:ReturnCount")
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // 获取Header
        final String authHeader = request.getHeader(Constants.AUTH_HEADER);
        // 存在token但不是tokenHead开头
        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            log.debug("authHeader is null come from TokenFilter, URL: {}", request::getRequestURI);
            filterChain.doFilter(request, response);
            return;
        }
        // 字段截取authToken
        var token = authHeader.split(" ")[1];
        // 没有token,直接放行
        if (token.isBlank()) {
            log.debug("token is null come from TokenFilter, URL: {}", request::getRequestURI);
            filterChain.doFilter(request, response);
            return;
        }
        // 根据authToken获取user信息
        final var userDetails = JwtUtil.getUserDetailsByToken(token);
        // token存在用户但未登录
        if (null != userDetails && null == SecurityContextHolder.getContext().getAuthentication()) {
            // 验证token是否有效，如果有效，将他重新放到用户对象里
            if (!JwtUtil.verifyAccessToken(token, userDetails)) {
                log.error("Token is invalid, username: {}", userDetails.getUsername());
                response.setCharacterEncoding(Constants.CHARACTER_ENCODING);
                response.setContentType(Constants.CONTENT_TYPE);
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.getWriter().write(
                        Objects.requireNonNull(
                                JacksonUtil.toJSONString(Result.failure(ResultCodeEnum.TOKEN_INVALID))));
                return;
            }
            log.debug("Token is valid, username: {}", userDetails::getUsername);
            var authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            // 重新设置到用户对象里
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        // 放行
        filterChain.doFilter(request, response);
    }
}
