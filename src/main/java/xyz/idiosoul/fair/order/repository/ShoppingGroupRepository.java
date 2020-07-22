package xyz.idiosoul.fair.order.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import xyz.idiosoul.fair.order.domain.model.cart.ShoppingGroup;

import java.util.List;
import java.util.Optional;

public interface ShoppingGroupRepository extends PagingAndSortingRepository<ShoppingGroup, Long> {
    List<ShoppingGroup> findAllByBuyerIdAndDeletedFalse(int buyerId);

    Optional<ShoppingGroup> findFirstByBuyerIdAndSellerIdAndDeletedIsFalse(int buyerId, int sellerId);
}
