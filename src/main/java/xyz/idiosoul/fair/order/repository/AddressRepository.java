package xyz.idiosoul.fair.order.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import xyz.idiosoul.fair.order.domain.model.address.ShippingAddress;

import java.util.Optional;

public interface AddressRepository extends PagingAndSortingRepository<ShippingAddress, Integer> {
    ShippingAddress findByUserIdAndIsDefaultIsTrueAndDeletedIsFalse(int userId);

    Optional<ShippingAddress> findOptionalByUserIdAndIdAndDeletedIsFalse(int userId, int id);
}
