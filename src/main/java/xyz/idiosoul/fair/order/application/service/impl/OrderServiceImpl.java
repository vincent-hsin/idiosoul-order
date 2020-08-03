package xyz.idiosoul.fair.order.application.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.idiosoul.fair.order.application.service.OrderService;
import xyz.idiosoul.fair.order.domain.model.cart.Cart;
import xyz.idiosoul.fair.order.domain.model.cart.CartFactory;
import xyz.idiosoul.fair.order.domain.model.cart.CartItem;
import xyz.idiosoul.fair.order.domain.model.order.LineItem;
import xyz.idiosoul.fair.order.domain.model.order.Order;
import xyz.idiosoul.fair.order.domain.model.order.OrderFactory;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderFactory orderFactory;
    private final CartFactory cartFactory;

    public OrderServiceImpl(OrderFactory orderFactory, CartFactory cartFactory) {
        this.orderFactory = orderFactory;
        this.cartFactory = cartFactory;
    }

    /**
     * 提交订单：根据购物车中的购物项创建订单,并移除购物车相应购物项
     *
     * @return
     */
    @Override
    @Transactional
    public long placeOrder(Integer customerId, Integer addressId, List<Long> cartItemIds) {
        Cart cart = cartFactory.ofCustomer(customerId);
        Order order = orderFactory.ofCustomer(customerId);
        List<CartItem> cartItems = cart.getCartItems(cartItemIds);
        List<LineItem> lineItems = new ArrayList<>();
        cartItems.forEach(cartItem -> {
            lineItems.add(new LineItem(cartItem.getSkuId(), cartItem.getQuantity()));
            cartItem.delete();
        });
        order.addLineItems(lineItems);
        return order.getId();
    }
}
