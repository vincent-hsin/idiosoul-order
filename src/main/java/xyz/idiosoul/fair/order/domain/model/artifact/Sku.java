package xyz.idiosoul.fair.order.domain.model.artifact;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import xyz.idiosoul.fair.order.infrastructure.EntityBase;

import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * @author vincent
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Sku extends EntityBase<Long> {
    int size;
    int color;
    int style;
    int unit;
    int brand;
    int manufacturer;
    BigDecimal price;
}
