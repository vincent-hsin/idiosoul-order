package xyz.idiosoul.fair.order.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.idiosoul.fair.order.infrastructure.model.Dict;

import java.util.List;
import java.util.Optional;

public interface DictRepository extends JpaRepository<Dict, Integer> {
    Optional<Dict> findByLabelAndCode(String label, Integer code);

    Optional<Dict> findByLabelAndText(String label, String Text);

    List<Dict> findAllByLabel(String label);
}
