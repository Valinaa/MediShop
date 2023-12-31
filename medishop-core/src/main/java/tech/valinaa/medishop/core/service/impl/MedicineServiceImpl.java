package tech.valinaa.medishop.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import tech.valinaa.medishop.core.dao.MedicineMapper;
import tech.valinaa.medishop.core.model.MedicineDO;
import tech.valinaa.medishop.core.service.MedicineService;

/**
 * @author Valinaa
 * @Date 2023/9/26 17:06
 * @Description
 */
@Service
public class MedicineServiceImpl extends ServiceImpl<MedicineMapper, MedicineDO> implements MedicineService {
}
