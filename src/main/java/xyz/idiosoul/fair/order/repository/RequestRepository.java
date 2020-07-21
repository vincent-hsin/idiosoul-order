package xyz.idiosoul.fair.order.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import xyz.idiosoul.fair.order.domain.model.request.Request;

import java.time.LocalDateTime;
import java.util.List;

public interface RequestRepository extends PagingAndSortingRepository<Request, Integer> {
    List<Request> findAllByStatusAndProcessDeadlineIsLessThanEqualAndDeletedIsFalse(byte status,
                                                                                    LocalDateTime processDeadline);
}
