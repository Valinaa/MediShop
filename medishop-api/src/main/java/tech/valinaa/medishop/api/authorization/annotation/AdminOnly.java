package tech.valinaa.medishop.api.authorization.annotation;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Valinaa
 * @Date 2023/10/8 8:32
 * @Description 仅限管理员
 */

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasRole('MANAGE_USER')")
public @interface AdminOnly {
}
