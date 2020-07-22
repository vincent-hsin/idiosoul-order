package xyz.idiosoul.fair.order.infrastructure.service.impl;

import org.springframework.stereotype.Service;
import xyz.idiosoul.fair.order.application.service.ShopService;
import xyz.idiosoul.fair.order.dto.ShopDTO;

/**
 * @author xinw
 */
@Service
public class ShopServiceImpl implements ShopService {
    @Override
    public ShopDTO getShopDetail(long shopId) {
        return new ShopDTO(1,"shop");
    }
}
