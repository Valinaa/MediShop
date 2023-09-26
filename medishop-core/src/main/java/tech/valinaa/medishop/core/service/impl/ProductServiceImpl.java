package tech.valinaa.medishop.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.valinaa.medishop.core.model.dao.ProductMapper;
import tech.valinaa.medishop.core.model.dataobject.ProductDO;
import tech.valinaa.medishop.core.service.ProductService;

/**
 * @author Valinaa
 * @Date 2023/9/26 17:06
 * @Description
 */
@Service
@Slf4j
public class ProductServiceImpl extends ServiceImpl<ProductMapper, ProductDO> implements ProductService {
}
