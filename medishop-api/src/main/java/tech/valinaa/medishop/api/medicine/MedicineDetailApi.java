package tech.valinaa.medishop.api.medicine;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tech.valinaa.medishop.api.authorization.BusinessOnly;
import tech.valinaa.medishop.api.authorization.LoginRequired;
import tech.valinaa.medishop.api.medicine.request.MedicineDetailRequest;
import tech.valinaa.medishop.api.medicine.response.MedicineDetailResponse;
import tech.valinaa.medishop.core.model.Result;

/**
 * @author Valinaa
 * @Date 2023/9/28 9:50
 */
@Tag(name = "药品详情Api", description = "药品详情相关Api")
@RequestMapping("/api/v1/medicine/detail")
@LoginRequired
public interface MedicineDetailApi {
    
    /**
     * 获取单个药品所有信息
     *
     * @param id 药品id
     * @return 药品详情
     */
    @Operation(summary = "获取单个药品所有信息", description = "根据id获取单个药品所有信息")
    @GetMapping("/{id}")
    Result<MedicineDetailResponse> getDetailOne(@PathVariable Long id);
    
    /**
     * 新增药品详情信息
     *
     * @param medicineDetailRequest 药品详情请求实体
     * @return 是否成功
     */
    @Operation(summary = "新增药品详情信息", description = "新增药品详情信息")
    @PostMapping
    @BusinessOnly
    Result<Boolean> addDetailOne(MedicineDetailRequest medicineDetailRequest);
    
}
