/*
 * 2016-2018 江西侬农网科技有限公司。ALL Rights Reserved
 */

package xyz.idiosoul.fair.order.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class SkuDetail {
    private String specificationName;
    private String specificationValue;
    private BigDecimal singleBuyUnitPrice;
    private BigDecimal groupBuyUnitPrice;
    private Map<Integer, BigDecimal> priceMap;
    private String skuImage;
    // 营销活动标志
    private boolean isLimitedTimePromotion;
    private boolean isPriceBreakDiscountPromotion;
    private boolean isHometownZonePromotion;
    private boolean isPovertyAlleviationZone;

    private Integer inventory;
}
