package xyz.idiosoul.fair.order.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderAddDTO {
//    @ApiModelProperty("地址id")
    Integer addressId;

//    @ApiModelProperty("优惠券id")
//    Long couponId;

//    @ApiModelProperty("积分抵扣数量")
//    Integer deductionIntegral;

//    @ApiModelProperty("发票信息")
//    Invoice invoice;

//    @ApiModelProperty("使用余额支付")
//    Boolean isUseBalance;

//    @ApiModelProperty("商户订单列表")
    List<Long> cartItems;

}
