package xyz.idiosoul.fair.order.domain.model.cart;

import org.springframework.stereotype.Component;
import xyz.idiosoul.fair.order.repository.CartRepository;

/**
 * 购物车 Factory
 *
 * @author xinw
 */
@Component
public class CartFactory {
    private CartRepository cartRepository;

    public CartFactory(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart ofCustomer(int customerId) {
        return cartRepository.findByCustomerIdAndDeletedFalse(customerId).orElseGet(() -> cartRepository.save(new Cart(customerId)));
    }
}
