package xyz.idiosoul.fair.order.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.idiosoul.fair.order.infrastructure.model.Dict;

import java.util.List;
import java.util.Optional;

public interface DictRepository extends JpaRepository<Dict, Integer> {
    Optional<Dict> findByLabelAndCodeAndDeleteAtIsNull(String label, Integer code);

    Optional<Dict> findByLabelAndTextAndDeleteAtIsNull(String label, String Text);

    List<Dict> findAllByLabelAndDeleteAtIsNull(String label);
}
