package xyz.idiosoul.fair.order.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author vincent
 */
@Data
public class SkuAddDTO {
    int size;
    int color;
    int style;
    int unit;
    int brand;
    int manufacturer;
    BigDecimal price;
}
