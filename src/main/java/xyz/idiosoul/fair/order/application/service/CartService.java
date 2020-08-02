package xyz.idiosoul.fair.order.application.service;

import xyz.idiosoul.fair.order.dto.CartItemAddDTO;

import java.util.Set;

/**
 * 购物车服务-接口
 *
 * @author vincent
 */
public interface CartService {

    /**
     * 添加
     *
     * @param cartItemAddDTO
     */
    void add(int customerId, CartItemAddDTO cartItemAddDTO);

    /**
     * 修改数量
     *
     * @param quantity
     */
    void editQuantity(int customerId, int skuId, int quantity);

    /**
     * 修改规格
     */
    void editSkuId(int customerId, int sourceSkuId, int targetSkuId);

    /**
     * 获取购物项数量
     *
     * @return
     */
    int getCartItemsCount(int customerId);

    /**
     * 删除
     */
    void deleteCartItems(int customerId, Set<Long> skuIds);
}
