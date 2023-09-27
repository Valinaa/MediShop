package tech.valinaa.medishop.web.controller.product;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import tech.valinaa.medishop.api.product.ProductApi;
import tech.valinaa.medishop.api.product.request.ProductRequest;
import tech.valinaa.medishop.api.product.response.ProductResponse;
import tech.valinaa.medishop.core.service.ProductService;
import tech.valinaa.medishop.web.converter.ProductConverter;

/**
 * @author Valinaa
 * @Date 2023/9/26 16:32
 * @Description
 */
@Slf4j
@RestController
//@RequiredArgsConstructor
public class ProductController implements ProductApi {

    private final ProductService productService;
    
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    
    @Override
    public ProductResponse getOne(Long id) {
        return ProductConverter.INSTANCE.daoToResponse(productService.getById(id));
    }
    
    @Override
    public boolean addOne(ProductRequest productRequest) {
        var productDO = ProductConverter.INSTANCE.reqEntityToDO(productRequest);
        return productService.save(productDO);
    }
}
