package xyz.idiosoul.fair.order.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import xyz.idiosoul.fair.order.domain.model.request.RequestEvent;

public interface RequestEventRepository extends PagingAndSortingRepository<RequestEvent, Integer> {
}
