package xyz.idiosoul.fair.order.application.service;

import xyz.idiosoul.fair.order.dto.ShoppingItemAddDTO;

import java.util.List;

public interface ShoppingCartService {

    /**
     * 添加
     *
     * @param buyerId
     * @param shoppingItemAddDTO
     */
    void add(int buyerId, ShoppingItemAddDTO shoppingItemAddDTO);


    void refreshPrice(int buyerId, int dataSpace);

    /**
     * 获取购物项数量
     *
     * @return
     */
    int getCartItemCount(int buyerId);

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
     * 删除
     *
     * @param orderItemIds
     */
    void delete(int buyerId, List<Long> orderItemIds);
}