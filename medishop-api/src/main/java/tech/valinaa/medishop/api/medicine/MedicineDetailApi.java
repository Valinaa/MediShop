package tech.valinaa.medishop.api.medicine;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tech.valinaa.medishop.api.medicine.request.MedicineDetailRequest;
import tech.valinaa.medishop.api.medicine.response.MedicineDetailResponse;

/**
 * @author Valinaa
 * @Date 2023/9/28 9:50
 */
@Tag(name = "药品详情Api", description = "药品详情相关Api")
@RequestMapping("/medicine/detail")
public interface MedicineDetailApi {
    
    @Operation(summary = "获取单个药品所有信息")
    @GetMapping
    MedicineDetailResponse getDetailOne(Long id);
    
    @Operation(summary = "新增药品详情")
    @PostMapping
    boolean addDetailOne(MedicineDetailRequest medicineDetailRequest);
    
    
}
