package tech.valinaa.medishop.api.authorization;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Valinaa
 * @Date 2023/10/8 10:08
 * @Description 仅允许商户或管理员访问
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasRole('MANAGE_GOODS')")
public @interface BusinessOnly {
}
