package xyz.idiosoul.fair.order.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import xyz.idiosoul.fair.order.domain.model.order.Order;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {
    List<Order> findAllByStatusAndDeletedIsFalseAndProcessDeadlineLessThan(Byte status,
                                                                           LocalDateTime processDeadline);

    List<Order> findByPaymentId(long paymentId);

    long countByBuyerIdAndStatusAndDeletedIsFalse(int buyerId, byte status);

    Order findFirstByBuyerIdAndSellerIdAndTypeAndDeletedIsFalse(int buyerId, int sellerId,
                                                                                  byte type);
}
