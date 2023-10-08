package tech.valinaa.medishop.api.medicine;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import tech.valinaa.medishop.api.Result;
import tech.valinaa.medishop.api.authorization.BusinessOnly;
import tech.valinaa.medishop.api.authorization.LoginRequired;
import tech.valinaa.medishop.api.medicine.request.MedicineRequest;
import tech.valinaa.medishop.api.medicine.response.MedicineResponse;
import tech.valinaa.medishop.api.page.BasePageRequest;
import tech.valinaa.medishop.api.page.PageResult;

/**
 * @author Valinaa
 * @Date 2023/9/26 17:50
 */
@Tag(name = "药品管理Api", description = "药品相关Api")
@RequestMapping("/api/v1/medicine")
@LoginRequired
public interface MedicineApi {
    
    /**
     * 获取单个药品信息
     *
     * @param id 药品id
     * @return 药品信息
     */
    @Operation(summary = "获取单个药品信息")
    @GetMapping("/{id}")
    Result<MedicineResponse> getOne(@PathVariable Long id);
    
    /**
     * 新增单个药品信息
     *
     * @param medicineRequest 药品信息
     * @return 是否成功
     */
    @Operation(summary = "新增单个药品信息")
    @PostMapping
    @BusinessOnly
    Result<Boolean> addOne(@RequestBody @Validated MedicineRequest medicineRequest);
    
    /**
     * 获取药品列表
     *
     * @param pageRequest 分页信息
     * @return 药品列表
     */
    @Operation(summary = "获取药品列表")
    @GetMapping("/list")
    // TODO 分页
    Result<PageResult<MedicineResponse>> getPageList(BasePageRequest pageRequest);
}
