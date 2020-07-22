package xyz.idiosoul.fair.order.dto;

import lombok.Data;
import xyz.idiosoul.fair.order.vo.ShoppingItemVO;

import java.util.ArrayList;
import java.util.List;

@Data
public class ShoppingGroupVO {
//    @ApiModelProperty("商铺id")
    int shopId;
//    @ApiModelProperty("商铺名称")
    String shopName;
//    @ApiModelProperty("订单活动分组（满减活动）")
//@ApiModelProperty("订单项列表")
List<ShoppingItemVO> orderItems = new ArrayList<>();
}