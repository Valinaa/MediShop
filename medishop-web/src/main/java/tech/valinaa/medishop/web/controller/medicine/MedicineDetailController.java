package tech.valinaa.medishop.web.controller.medicine;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import tech.valinaa.medishop.api.medicine.MedicineDetailApi;
import tech.valinaa.medishop.api.medicine.request.MedicineDetailRequest;
import tech.valinaa.medishop.api.medicine.response.MedicineDetailResponse;
import tech.valinaa.medishop.core.model.dataobject.MedicineDetailDO;
import tech.valinaa.medishop.core.service.MedicineDetailService;
import tech.valinaa.medishop.core.service.MedicineService;
import tech.valinaa.medishop.web.converter.MedicineConverter;

/**
 * @author Valinaa
 * @Date 2023/9/28 14:48
 */
@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MedicineDetailController implements MedicineDetailApi {
    
    private final MedicineService medicineService;
    private final MedicineDetailService medicineDetailService;
    @Override
    public MedicineDetailResponse getDetailOne(Long id) {
        var medicine = medicineService.getById(id);
        var medicineDetail = medicineDetailService.getOne(new LambdaQueryWrapper<MedicineDetailDO>()
                .eq(MedicineDetailDO::getMedicineId, id));
        return MedicineConverter.INSTANCE.mergeDao2DetailResponse(medicine, medicineDetail);
    }
    
    @Override
    public boolean addDetailOne(MedicineDetailRequest medicineDetailRequest) {
        return medicineDetailService.saveOrUpdate(MedicineConverter.INSTANCE.req2DetailDO(medicineDetailRequest));
    }
}
