/*
 * 2016-2018 江西侬农网科技有限公司。ALL Rights Reserved
 */

package xyz.idiosoul.fair.order.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class ProductDTO {
    private Long productId;
    private Integer shopId;
    private String productName;
    private String productImage;
    private Integer productTypeId;
    private String productTypeName;
    private Integer deliveryType;
    private List<String> deliveryDates;
    private LocalDate beginDate;
    private LocalDate endDate;
    private Integer groupThreshold;
    private String vr;
    private String video;
    private Integer deliveryCostType;
    private BigDecimal fixedDeliveryCost;
    private Integer deliveryCostTemplateId;
    private BigDecimal price;
}
