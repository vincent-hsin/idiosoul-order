package xyz.idiosoul.fair.order.domain.model.cart;

import org.springframework.stereotype.Component;
import xyz.idiosoul.fair.order.repository.ShoppingGroupRepository;

import java.util.List;
import java.util.Optional;

/**
 * 购物车购物组 Factory
 *
 * @author xinw
 */
@Component
public class ShoppingGroupFactory {
    private ShoppingGroupRepository shoppingGroupRepository;

    public ShoppingGroupFactory(ShoppingGroupRepository shoppingGroupRepository) {
        this.shoppingGroupRepository = shoppingGroupRepository;
    }

    public ShoppingGroup createShoppingGroup(int buyerId, int sellerId) {
        ShoppingGroup shoppingGroup = shoppingGroupRepository.save(new ShoppingGroup(buyerId, sellerId));
        return shoppingGroup;
    }

    public Optional<ShoppingGroup> getShoppingGroup(int buyerId, int sellerId) {
        return shoppingGroupRepository.findFirstByBuyerIdAndSellerIdAndDeletedIsFalse(buyerId, sellerId);
    }

    public List<ShoppingGroup> getShoppingGroups(int buyerId) {

        return shoppingGroupRepository.findAllByBuyerIdAndDeletedFalse(buyerId);
    }
}
