package tech.valinaa.medishop.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import tech.valinaa.medishop.api.Result;
import tech.valinaa.medishop.api.medicine.MedicineApi;
import tech.valinaa.medishop.api.medicine.request.MedicineRequest;
import tech.valinaa.medishop.api.medicine.response.MedicineResponse;
import tech.valinaa.medishop.api.page.BasePageRequest;
import tech.valinaa.medishop.api.page.PageResult;
import tech.valinaa.medishop.core.converter.MedicineConverter;
import tech.valinaa.medishop.core.service.MedicineService;

/**
 * @author Valinaa
 * @Date 2023/9/26 16:32
 */
@RestController
@RequiredArgsConstructor
public class MedicineController implements MedicineApi {
    
    private final MedicineService medicineService;
    
    @Override
    public Result<MedicineResponse> getOne(Long id) {
        return Result.convert(
                MedicineConverter.INSTANCE.dao2Response(medicineService.getById(id)));
    }
    
    @Override
    public Result<Boolean> addOne(MedicineRequest medicineRequest) {
        var medicineDO = MedicineConverter.INSTANCE.req2DO(medicineRequest);
        return Result.convert(medicineService.save(medicineDO));
    }
    
    @Override
    public Result<PageResult<MedicineResponse>> getPageList(BasePageRequest pageRequest) {
        return null;
    }
}
