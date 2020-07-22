package xyz.idiosoul.fair.order.domain.model.cart;

import org.springframework.stereotype.Component;
import xyz.idiosoul.fair.order.repository.ShoppingGroupRepository;

import java.util.List;

/**
 * @author vincent
 */
@Component
public class ShoppingCartFactory {
    private ShoppingGroupFactory shoppingGroupFactory;
    private ShoppingGroupRepository shoppingGroupRepository;

    public ShoppingCartFactory(ShoppingGroupFactory shoppingGroupFactory, ShoppingGroupRepository shoppingGroupRepository) {
        this.shoppingGroupFactory = shoppingGroupFactory;
        this.shoppingGroupRepository = shoppingGroupRepository;
    }

    public ShoppingCart getShoppingCart(int userId) {
        List<ShoppingGroup> shoppingGroups =
                shoppingGroupRepository.findAllByBuyerIdAndDeletedFalse(userId);
        return new ShoppingCart(shoppingGroupFactory, shoppingGroups);
    }
}
