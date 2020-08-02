package xyz.idiosoul.fair.order.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import xyz.idiosoul.fair.order.domain.model.cart.Cart;

import java.util.Optional;

public interface CartRepository extends PagingAndSortingRepository<Cart, Long> {
    Optional<Cart> findByCustomerIdAndDeletedFalse(int customerId);
}
