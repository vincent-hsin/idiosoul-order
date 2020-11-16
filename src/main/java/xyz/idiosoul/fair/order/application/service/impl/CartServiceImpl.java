package xyz.idiosoul.fair.order.application.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.idiosoul.fair.order.application.service.CartService;
import xyz.idiosoul.fair.order.constant.RedisKeyConsts;
import xyz.idiosoul.fair.order.domain.model.cart.Cart;
import xyz.idiosoul.fair.order.domain.model.cart.CartFactory;
import xyz.idiosoul.fair.order.dto.CartItemAddDTO;
import xyz.idiosoul.fair.order.vo.CheckoutVO;

import java.util.List;
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
    private final StringRedisTemplate stringRedisTemplate;

    public CartServiceImpl(CartFactory cartFactory, StringRedisTemplate stringRedisTemplate) {
        this.cartFactory = cartFactory;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    @Transactional
    public void add(int customerId, CartItemAddDTO cartItemAddDTO) {
        Cart cart = cartFactory.ofCustomer(customerId);
        cart.add(cartItemAddDTO);
        stringRedisTemplate.opsForValue().increment(String.format(RedisKeyConsts.CUSTOMER_CART_ITEM_COUNT,customerId),1);
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

    @Override
    public CheckoutVO checkout(int customerId, List<Long> cartItemIds) {
        return null;
    }
}
