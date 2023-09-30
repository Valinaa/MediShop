package tech.valinaa.medishop.web.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import tech.valinaa.medishop.api.medicine.request.MedicineDetailRequest;
import tech.valinaa.medishop.api.medicine.request.MedicineRequest;
import tech.valinaa.medishop.api.medicine.response.MedicineDetailResponse;
import tech.valinaa.medishop.api.medicine.response.MedicineResponse;
import tech.valinaa.medishop.core.model.dataobject.MedicineDO;
import tech.valinaa.medishop.core.model.dataobject.MedicineDetailDO;

/**
 * @author Valinaa
 * @Date 2023/9/26 17:50
 * @Description 药品对象转化器
 */

@Mapper
public interface MedicineConverter extends BaseConverter<MedicineRequest, MedicineResponse, MedicineDO> {
    MedicineConverter INSTANCE = Mappers.getMapper(MedicineConverter.class);
    
    /**
     * 转化药品详情
     * @param dao 药品DO对象
     * @param detailDao 药品详情DO对象
     * @return 药品详情响应实体
     */
    @Mapping(target = "", source = "detailDao.id", ignore = true)
    MedicineDetailResponse mergeDao2DetailResponse(MedicineDO dao, MedicineDetailDO detailDao);
    
    MedicineDetailDO req2DetailDO(MedicineDetailRequest medicineDetailRequest);
}
