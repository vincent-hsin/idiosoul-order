package xyz.idiosoul.fair.order.application.service;

import xyz.idiosoul.fair.order.dto.OrderAddDTO;

import java.util.List;

public interface OrderService {


    /**
     * 下订单
     *
     * @param orderAddDTO
     */
    long placeOrder(Integer customerId,Integer addressId, List<Long> cartItemIds);
}
