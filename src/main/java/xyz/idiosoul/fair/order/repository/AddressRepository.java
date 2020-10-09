package xyz.idiosoul.fair.order.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import xyz.idiosoul.fair.order.domain.model.address.Address;

import java.util.Optional;

public interface AddressRepository extends PagingAndSortingRepository<Address, Integer> {
    Address findByUserIdAndIsDefaultIsTrueAndDeletedIsFalse(int userId);

    Optional<Address> findOptionalByUserIdAndIdAndDeletedIsFalse(int userId, int id);
}
