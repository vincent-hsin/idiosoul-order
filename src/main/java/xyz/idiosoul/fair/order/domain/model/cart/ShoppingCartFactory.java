package xyz.idiosoul.fair.order.domain.model.cart;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 购物车 Factory
 *
 * @author vincent
 */
@Component
public class ShoppingCartFactory {
    private ShoppingGroupFactory shoppingGroupFactory;

    public ShoppingCartFactory(ShoppingGroupFactory shoppingGroupFactory) {
        this.shoppingGroupFactory = shoppingGroupFactory;
    }

    public ShoppingCart getShoppingCart(int buyerId) {
        List<ShoppingGroup> shoppingGroups =
                shoppingGroupFactory.getShoppingGroups(buyerId);
        return new ShoppingCart(shoppingGroupFactory, shoppingGroups);
    }
}
