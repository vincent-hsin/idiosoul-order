package xyz.idiosoul.fair.order.application.service;

import xyz.idiosoul.fair.order.dto.ProductDTO;
import xyz.idiosoul.fair.order.dto.SkuDetail;

/**
 * 商品服务接口
 */
public interface ProductService {
    /**
     * 获取商品信息
     *
     * @param productId
     * @return
     */
    ProductDTO getDetail(long productId);

    /**
     * 获取sku信息
     *
     * @param skuId
     * @return
     */
    SkuDetail getSkuDetail(long skuId);

    /**
     * 减库存
     *
     * @param productId
     * @param specificationId
     * @param count
     */
    void decreaseInventory(long productId, long specificationId, int count);

    void increaseInventory(long productId, long specificationId, int count);
}
