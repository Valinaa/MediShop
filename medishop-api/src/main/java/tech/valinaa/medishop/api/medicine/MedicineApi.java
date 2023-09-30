package tech.valinaa.medishop.api.medicine;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tech.valinaa.medishop.api.page.BasePageRequest;
import tech.valinaa.medishop.api.page.PageResult;
import tech.valinaa.medishop.api.medicine.request.MedicineRequest;
import tech.valinaa.medishop.api.medicine.response.MedicineResponse;

/**
 * @author Valinaa
 * @Date 2023/9/26 17:50
 */
@Tag(name = "药品管理Api", description = "药品相关Api")
@RequestMapping("/medicine")
public interface MedicineApi {
    
    @Operation(summary = "获取单个药品信息")
    @GetMapping
    MedicineResponse getOne(Long id);
    
    @Operation(summary = "新增单个药品信息")
    @PostMapping
    boolean addOne(MedicineRequest medicineRequest);
    
    @Operation(summary = "获取药品列表")
    @GetMapping("/list")
    // TODO 分页
    PageResult<MedicineResponse> getPageList(BasePageRequest pageRequest);
}
