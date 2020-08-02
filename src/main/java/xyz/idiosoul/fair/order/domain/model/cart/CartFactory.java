package xyz.idiosoul.fair.order.domain.model.cart;

import org.springframework.stereotype.Component;
import xyz.idiosoul.fair.order.repository.CartRepository;

import java.util.Optional;

/**
 * 购物车购物组 Factory
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
        Optional<Cart> optionalCart = cartRepository.findByCustomerIdAndDeletedFalse(customerId);
        Cart cart;
        if (optionalCart.isPresent()) {
            cart = optionalCart.get();
        } else {
            cart = createCart(customerId);
        }
        return cart;
    }

    private Cart createCart(int customerId) {
        return cartRepository.save(new Cart(customerId));
    }
}
