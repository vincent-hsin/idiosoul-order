package xyz.idiosoul.fair.order.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import xyz.idiosoul.fair.order.domain.model.cart.ShoppingGroup;

import java.util.List;

public interface ShoppingGroupRepository extends PagingAndSortingRepository<ShoppingGroup, Long> {
    List<ShoppingGroup> findAllByBuyerIdAndDeletedFalse(int buyerId);

    ShoppingGroup findFirstByBuyerIdAndSellerIdAndDataSpaceAndDeletedIsFalse(int buyerId, int sellerId, int dataSpace);
}
