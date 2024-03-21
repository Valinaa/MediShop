package tech.valinaa.medishop.neo4j.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.valinaa.medishop.neo4j.dao.CategoryRepository;
import tech.valinaa.medishop.neo4j.model.CategoryEntity;

import java.util.List;

/**
 * Category Service
 *
 * @author Valinaa
 * @Date 2024/3/21 下午3:10
 */
@Service
@RequiredArgsConstructor
public final class CategoryService {
    private final CategoryRepository categoryRepository;
    
    /**
     * 根据药品ID查询关联资源
     *
     * @param drugId 药品ID
     * @return 带有关联资源的药品列表
     */
    public List<CategoryEntity> findListByDrugId(String drugId) {
        return categoryRepository.findListByDrugId(drugId);
    }
}
