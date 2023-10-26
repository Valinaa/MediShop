package tech.valinaa.medishop.core.converter.annotation;

import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Valinaa
 * @Date 2023/10/3 21:58
 * @Description 映射默认ignore
 */
@Retention(RetentionPolicy.CLASS)
@Mappings(value = {
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "gmtCreated", ignore = true),
        @Mapping(target = "gmtModified", ignore = true),
        @Mapping(target = "creator", ignore = true),
        @Mapping(target = "modifier", ignore = true),
        @Mapping(target = "deleted", ignore = true)
})
public @interface MappingIgnore {
}
