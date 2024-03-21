package tech.valinaa.medishop.neo4j.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.valinaa.medishop.neo4j.dao.PriceRepository;
import tech.valinaa.medishop.neo4j.model.PriceEntity;

import java.util.List;

/**
 * Product Service
 *
 * @author Valinaa
 * @Date 2024/3/21 下午3:11
 */
@Service
@RequiredArgsConstructor
public final class PriceService {
    private final PriceRepository priceRepository;
    
    /**
     * 根据药品ID查询关联资源
     *
     * @param drugId 药品ID
     * @return 带有关联资源的药品列表
     */
    public List<PriceEntity> findListByDrugId(String drugId) {
        return priceRepository.findListByDrugId(drugId);
    }
}
