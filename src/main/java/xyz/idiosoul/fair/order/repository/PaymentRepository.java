package xyz.idiosoul.fair.order.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import xyz.idiosoul.fair.order.domain.model.payment.Payment;

public interface PaymentRepository extends PagingAndSortingRepository<Payment, Long> {
}
