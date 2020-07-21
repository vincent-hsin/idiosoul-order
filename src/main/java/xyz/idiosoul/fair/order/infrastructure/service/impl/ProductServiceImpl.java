package xyz.idiosoul.fair.order.infrastructure.service.impl;

import org.springframework.stereotype.Service;
import xyz.idiosoul.fair.order.application.service.ProductService;
import xyz.idiosoul.fair.order.dto.ProductDTO;
import xyz.idiosoul.fair.order.dto.SkuDetail;

/**
 * @author xinw
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public ProductDTO getDetail(long productId) {
        return null;
    }

    @Override
    public SkuDetail getSkuDetail(long skuId) {
        return null;
    }

    @Override
    public void decreaseInventory(long productId, long specificationId, int count) {

    }

    @Override
    public void increaseInventory(long productId, long specificationId, int count) {

    }
}
