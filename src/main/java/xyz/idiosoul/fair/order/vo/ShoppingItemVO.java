/*
 * 2016-2018 江西侬农网科技有限公司。ALL Rights Reserved
 */

package xyz.idiosoul.fair.order.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ShoppingItemVO {
//    @ApiModelProperty("购物项id")
    private Long itemId;
//    @ApiModelProperty("商品id")
    private Long productId;
//    @ApiModelProperty("商品名称")
    private String productName;
//    @ApiModelProperty("商品图片")
    private String productImage;
//    @ApiModelProperty("商品规格id")
    private Long specificationId;
//    @ApiModelProperty("商品规格名称")
    private String specificationName;
//    @ApiModelProperty("商品规格值")
    private String specificationValue;
//    @ApiModelProperty("商品单价")
    private BigDecimal unitPrice;
//    @ApiModelProperty("购买数量")
    private Integer quantity;
    @JsonIgnore
//    @ApiModelProperty("会员返利")
    private BigDecimal memberRebate = BigDecimal.ZERO; // todo member
//    @ApiModelProperty("促销类型")
//    private EnumSet<FairOrderPromotionTypeEnum> promotionTypes;
    private String promotionTypes;
//    @ApiModelProperty("拼团价")
    private BigDecimal groupBuyPrice;
//    @ApiModelProperty("状态（VALID：有效，OUT_OF_STOCK：库存不足）")
//    private ShoppingItemStatusViewEnum status;
//    @ApiModelProperty("限时购倒计时")
//    private Long limitedTimeCountdown;
//    @JsonIgnore
//    @ApiModelProperty("满减活动规则")
//    private String priceBreakDiscountRule;
//    @ApiModelProperty("小计")
//    private BigDecimal subtotalAmount;
//
//    public BigDecimal getSubtotalAmount() {
//        return unitPrice.multiply(BigDecimal.valueOf(quantity));
//    }
}