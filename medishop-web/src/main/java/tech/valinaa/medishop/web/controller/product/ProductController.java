package tech.valinaa.medishop.web.controller.product;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.valinaa.medishop.api.product.ProductApi;
import tech.valinaa.medishop.api.product.request.ProductRequest;
import tech.valinaa.medishop.api.product.response.ProductResponse;
import tech.valinaa.medishop.core.service.ProductService;
import tech.valinaa.medishop.web.converter.product.ProductConverter;

/**
 * @author Valinaa
 * @Date 2023/9/26 16:32
 * @Description MediShop
 */
@Slf4j
@RestController
@AllArgsConstructor
public class ProductController implements ProductApi {
    
    private final ProductService productService;
    
    @Override
    public ProductResponse getOne() {
        return productService.get;
    }
    
    @Override
    public boolean addOne(ProductRequest productRequest) {
        var productDO = ProductConverter.INSTANCE.toProductDO(productRequest);
        return productService.save(productDO);
    }
}
