package tech.valinaa.medishop.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import tech.valinaa.medishop.core.dao.MedicineDetailMapper;
import tech.valinaa.medishop.core.model.MedicineDetailDO;
import tech.valinaa.medishop.core.service.MedicineDetailService;

/**
 * @author Valinaa
 * @Date 2023/9/28 14:52
 */
@Service
public class MedicineDetailServiceImpl extends ServiceImpl<MedicineDetailMapper, MedicineDetailDO>
        implements MedicineDetailService {
}
