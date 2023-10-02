package tech.valinaa.medishop.web.controller.medicine;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import tech.valinaa.medishop.api.medicine.MedicineApi;
import tech.valinaa.medishop.api.medicine.request.MedicineRequest;
import tech.valinaa.medishop.api.medicine.response.MedicineResponse;
import tech.valinaa.medishop.api.page.BasePageRequest;
import tech.valinaa.medishop.api.page.PageResult;
import tech.valinaa.medishop.core.service.MedicineService;
import tech.valinaa.medishop.web.converter.MedicineConverter;

/**
 * @author Valinaa
 * @Date 2023/9/26 16:32
 * @Description
 */
@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MedicineController implements MedicineApi {
    
    private final MedicineService medicineService;
    
    @Override
    public MedicineResponse getOne(Long id) {
        return MedicineConverter.INSTANCE.dao2Response(medicineService.getById(id));
    }
    
    @Override
    public boolean addOne(MedicineRequest medicineRequest) {
        var medicineDO = MedicineConverter.INSTANCE.req2DO(medicineRequest);
        return medicineService.save(medicineDO);
    }
    
    @Override
    public PageResult<MedicineResponse> getPageList(BasePageRequest pageRequest) {
        return null;
    }
}
