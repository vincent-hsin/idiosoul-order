package xyz.idiosoul.fair.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.idiosoul.fair.order.domain.model.artifact.Sku;

/**
 * @author vincent
 */
public interface SkuRepository extends JpaRepository<Sku, Long> {
}
