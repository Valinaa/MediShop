package tech.valinaa.medishop.web.converter.product;

/**
 * @author Valinaa
 * @Date 2023/9/26 17:50
 * @Description
 */

//@Mapper
public interface ProductConverter {
    ProductConverter INSTANCE = Mappers.getMapper(ProductConverter.class);
}
