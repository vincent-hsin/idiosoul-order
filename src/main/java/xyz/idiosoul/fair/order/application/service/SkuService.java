package xyz.idiosoul.fair.order.application.service;

import xyz.idiosoul.fair.order.dto.SkuAddDTO;

/**
 * Stock Keeping Unit 服务接口
 *
 * @author vincent
 */
public interface SkuService {
    void add(int customerId, SkuAddDTO skuAddDTO);

    void delete(long skuId);
}
