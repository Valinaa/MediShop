package tech.valinaa.medishop.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import tech.valinaa.medishop.api.Result;
import tech.valinaa.medishop.api.medicine.MedicineDetailApi;
import tech.valinaa.medishop.api.medicine.request.MedicineDetailRequest;
import tech.valinaa.medishop.api.medicine.response.MedicineDetailResponse;
import tech.valinaa.medishop.core.model.dataobject.MedicineDetailDO;
import tech.valinaa.medishop.core.service.MedicineDetailService;
import tech.valinaa.medishop.core.service.MedicineService;
import tech.valinaa.medishop.web.converter.MedicineConverter;

/**
 * @author Valinaa
 * @Date 2023/10/8 13:39
 */
@RestController
@RequiredArgsConstructor
public class MedicineDetailController implements MedicineDetailApi {
    
    private final MedicineService medicineService;
    private final MedicineDetailService medicineDetailService;
    
    @Override
    public Result<MedicineDetailResponse> getDetailOne(Long id) {
        var medicine = medicineService.getById(id);
        var medicineDetail = medicineDetailService.getOne(medicineDetailService.lambdaQuery()
                .eq(MedicineDetailDO::getMedicineId, id));
        return Result.convert(
                MedicineConverter.INSTANCE.mergeDao2DetailResponse(medicine, medicineDetail));
    }
    
    @Override
    public Result<Boolean> addDetailOne(MedicineDetailRequest medicineDetailRequest) {
        return Result.convert(
                medicineDetailService.saveOrUpdate(
                        MedicineConverter.INSTANCE.req2DetailDO(medicineDetailRequest)));
    }
}
