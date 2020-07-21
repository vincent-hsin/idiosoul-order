package xyz.idiosoul.fair.order.application.service;

import xyz.idiosoul.fair.order.dto.ShopDTO;

/**
 * 店铺服务接口
 */
public interface ShopService {
    /**
     * 获取店铺信息
     *
     * @param shopId
     * @return
     */
    ShopDTO getShopDetail(long shopId);


}
