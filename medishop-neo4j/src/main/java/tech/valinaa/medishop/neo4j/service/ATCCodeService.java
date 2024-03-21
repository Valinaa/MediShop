package tech.valinaa.medishop.neo4j.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.valinaa.medishop.neo4j.dao.ATCCodeRepository;
import tech.valinaa.medishop.neo4j.model.ATCCodeEntity;

import java.util.List;

/**
 * ATCCode 服务类
 *
 * @author Valinaa
 * @Date 2024/3/21 下午3:08
 */
@Service
@RequiredArgsConstructor
public final class ATCCodeService {
    private final ATCCodeRepository atcCodeRepository;
    
    /**
     * 根据药品ID查询关联资源
     *
     * @param drugId 药品ID
     * @return 带有关联资源的药品列表
     */
    public List<ATCCodeEntity> findListByDrugId(String drugId) {
        return atcCodeRepository.findListByDrugId(drugId);
    }
}
