/*
 * 2016-2018 江西侬农网科技有限公司。ALL Rights Reserved
 */

package xyz.idiosoul.fair.order.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderOfShopVO {
    //    @ApiModelProperty("商铺id")
    int shopId;
    //    @ApiModelProperty("商铺名称")
    String shopName;
    //    @ApiModelProperty("运费")
//    BigDecimal deliveryCost; // todo del?
//    @ApiModelProperty("小计件数")
//    Integer subtotalPieceCount; // todo del?
//    @ApiModelProperty("小计金额")
//    BigDecimal subtotalAmount; // todo del?
//    @ApiModelProperty("备注")
//    String remark; // todo del？
//    @ApiModelProperty("订单项列表")
    List<ShoppingItemVO> orderItems = new ArrayList<>();
}