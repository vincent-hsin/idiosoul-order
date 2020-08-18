package xyz.idiosoul.fair.order.domain.model.artifact;

import xyz.idiosoul.fair.order.infrastructure.EntityBase;

import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * @author vincent
 */
@Entity
public class Sku extends EntityBase<Long> {
    int size;
    int color;
    int unit;
    BigDecimal price;
}
