package xyz.idiosoul.fair.order.application.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.idiosoul.fair.order.application.service.CartService;
import xyz.idiosoul.fair.order.domain.model.cart.Cart;
import xyz.idiosoul.fair.order.domain.model.cart.CartFactory;
import xyz.idiosoul.fair.order.dto.CartItemAddDTO;

import java.util.Set;

/**
 * 购物车服务-实现
 *
 * @author vincent
 */
@Slf4j
@Service
public class CartServiceImpl implements CartService {
    private final CartFactory cartFactory;

    public CartServiceImpl(CartFactory cartFactory) {
        this.cartFactory = cartFactory;
    }

    @Override
    @Transactional
    public void add(int customerId, CartItemAddDTO cartItemAddDTO) {
        Cart cart = cartFactory.ofCustomer(customerId);
        cart.add(cartItemAddDTO);
    }

    @Override
    @Transactional
    public int getCartItemsCount(int customerId) {
        Cart cart = cartFactory.ofCustomer(customerId);
        return cart.countItems();
    }

    @Override
    @Transactional
    public void editQuantity(int customerId, int skuId, int quantity) {
        Cart cart = cartFactory.ofCustomer(customerId);
        cart.editItemQuantity(skuId, quantity);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void editSkuId(int customerId, int sourceSkuId, int targetSkuId) {
        Cart cart = cartFactory.ofCustomer(customerId);
        cart.editItemSku(sourceSkuId, targetSkuId);
    }


    @Override
    @Transactional
    public void deleteCartItems(int customerId, Set<Long> skuIds) {
        Cart cart = cartFactory.ofCustomer(customerId);
        cart.delete(skuIds);
    }
}
