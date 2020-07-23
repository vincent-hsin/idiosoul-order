package xyz.idiosoul.fair.order.application.service;

import xyz.idiosoul.fair.order.dto.ShoppingItemAddDTO;

import java.util.List;

/**
 * 购物车服务-接口
 *
 * @author vincent
 */
public interface ShoppingCartService {

    /**
     * 添加
     *
     * @param buyerId
     * @param shoppingItemAddDTO
     */
    void add(int buyerId, ShoppingItemAddDTO shoppingItemAddDTO);

    /**
     * 修改数量
     *
     * @param quantity
     */
    void editQuantity(int buyerId, int shoppingItemId, int quantity);

    /**
     * 修改规格
     *
     * @param specificationId
     */
    void editSpecificationId(int buyerId, int shoppingItemId, int specificationId);

    /**
     * 获取购物项数量
     *
     * @return
     */
    int getCartItemCount(int buyerId);

    /**
     * 删除
     *
     * @param orderItemIds
     */
    void deleteCartItems(int buyerId, List<Long> orderItemIds);
}
