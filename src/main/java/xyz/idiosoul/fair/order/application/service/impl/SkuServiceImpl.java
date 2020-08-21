package xyz.idiosoul.fair.order.application.service.impl;

import org.springframework.stereotype.Service;
import xyz.idiosoul.fair.order.application.service.SkuService;
import xyz.idiosoul.fair.order.domain.model.artifact.Sku;
import xyz.idiosoul.fair.order.domain.model.artifact.SkuFactory;
import xyz.idiosoul.fair.order.dto.SkuAddDTO;

/**
 * Stock Keeping Unit 服务实现
 *
 * @author vincent
 */
@Service
public class SkuServiceImpl implements SkuService {
    private SkuFactory skuFactory;

    public SkuServiceImpl(SkuFactory skuFactory) {
        this.skuFactory = skuFactory;
    }

    @Override
    public void add(int customerId, SkuAddDTO skuAddDTO) {
        skuFactory.of(skuAddDTO);
    }

    @Override
    public void delete(long skuId) {
        Sku sku = skuFactory.of(skuId);
        sku.delete();
    }
}
