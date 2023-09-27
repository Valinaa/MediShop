package tech.valinaa.medishop.web.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tech.valinaa.medishop.api.product.request.ProductRequest;
import tech.valinaa.medishop.api.product.response.ProductResponse;
import tech.valinaa.medishop.core.model.dataobject.ProductDO;

/**
 * @author Valinaa
 * @Date 2023/9/26 17:50
 * @Description 药品对象转化器
 */

@Mapper
public interface ProductConverter extends BaseConverter<ProductRequest, ProductResponse, ProductDO> {
    ProductConverter INSTANCE = Mappers.getMapper(ProductConverter.class);
}
