package xyz.idiosoul.fair.order.vo;

//import com.nongnongyigou.service.order.constant.RefundStatusEnum;
//import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItem4CustomerVO {
    private Long itemId;
//    @ApiModelProperty("商品id")
//    private Long productId;
//    @ApiModelProperty("商品名称")
//    private String productName;
//    @ApiModelProperty("商品图片")
//    private String productImage;
//    @ApiModelProperty("skuId")
    private Integer skuId;
//    @ApiModelProperty("商品规格名称")
//    private String specificationName;
//    @ApiModelProperty("商品规格值")
//    private String specificationValue;
//    @ApiModelProperty("商品单价")
    private BigDecimal unitPrice;
//    @ApiModelProperty("购买数量")
    private Integer quantity;
    //    @ApiModelProperty("订单项状态")
//    private OrderItemStatusViewEnum orderItemStatus;
//    @ApiModelProperty("售后状态")
//    private RefundStatusEnum requestStatus;
//    @ApiModelProperty("售后id")
//    private Integer requestId;
    //    @ApiModelProperty("小计")
//    private BigDecimal subtotalAmount;
//    @ApiModelProperty("会员返利")
//    private BigDecimal memberRebate = BigDecimal.ZERO; // todo 业务规则

//    public BigDecimal getSubtotalAmount() {
//        return unitPrice.multiply(BigDecimal.valueOf(quantity));
//    }
}