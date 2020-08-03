package xyz.idiosoul.fair.order.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import xyz.idiosoul.fair.order.domain.model.order.Order;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {
}
