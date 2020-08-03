package xyz.idiosoul.fair.order.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

//@ApiModel("结算信息")
@Data
public class CheckoutVO {
    //    @ApiModelProperty("会员折扣金额")
//    BigDecimal memberDiscount;
//    @ApiModelProperty("可用优惠券id列表")
//    List<Integer> availableCouponIds;
//    @ApiModelProperty("合计件数")
    private Integer totalPieceCount;
//    @ApiModelProperty("合计金额")
    private BigDecimal totalAmount;
//    @ApiModelProperty("购物清单")
    private List<ShoppingItemVO> cartItems;

//    @ApiModelProperty("活动（满减）优惠金额")
    private BigDecimal promotionDiscountAmount;

//    @ApiModelProperty("金币抵扣金额")
    private BigDecimal goldCoinDiscountAmount;
//    @ApiModelProperty("金币抵扣备注")
    private String goldCoinDiscountRemark;
}
