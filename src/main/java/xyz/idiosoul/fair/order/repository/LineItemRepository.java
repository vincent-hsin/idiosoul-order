package xyz.idiosoul.fair.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.idiosoul.fair.order.domain.model.order.LineItem;

public interface LineItemRepository extends JpaRepository<LineItem, Long> {
}
