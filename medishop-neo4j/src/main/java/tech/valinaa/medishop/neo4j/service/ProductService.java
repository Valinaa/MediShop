package tech.valinaa.medishop.neo4j.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.valinaa.medishop.neo4j.dao.ProductRepository;
import tech.valinaa.medishop.neo4j.model.ProductEntity;

import java.util.List;

/**
 * Product Service
 *
 * @author Valinaa
 * @Date 2024/3/21 下午3:11
 */
@Service
@RequiredArgsConstructor
public final class ProductService {
    private final ProductRepository productRepository;
    
    /**
     * 根据药品ID查询关联资源
     *
     * @param drugId 药品ID
     * @return 带有关联资源的药品列表
     */
    public List<ProductEntity> findListByDrugId(String drugId) {
        return productRepository.findListByDrugId(drugId);
    }
}
