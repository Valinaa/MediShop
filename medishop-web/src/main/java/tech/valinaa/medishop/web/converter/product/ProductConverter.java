package tech.valinaa.medishop.web.converter.product;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author Valinaa
 * @Date 2023/9/26 17:50
 * @Description
 */

@Mapper
public interface ProductConverter {
    ProductConverter INSTANCE = Mappers.getMapper(ProductConverter.class);
}
