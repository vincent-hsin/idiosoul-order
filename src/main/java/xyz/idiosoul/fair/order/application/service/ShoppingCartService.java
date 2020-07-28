package xyz.idiosoul.fair.order.application.service;

import xyz.idiosoul.fair.order.dto.ShoppingItemAddDTO;

import java.util.List;
import java.util.Map;
import java.util.Set;

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
    void editQuantity(int buyerId, int sellerId, int skuId, int quantity);

    /**
     * 修改规格
     *
     */
    void editSkuId(int buyerId, int sellerId, int sourceSkuId, int targetSkuId);

    /**
     * 获取购物项数量
     *
     * @return
     */
    int getCartItemCount(int buyerId);

    /**
     * 删除
     *
     * @param shoppingMap
     */
    void deleteCartItems(int buyerId, Map<Integer, Set<Integer>> shoppingMap);
}
