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
    private Long skuId;
//    @ApiModelProperty("商品单价")
//    private BigDecimal unitPrice;
//    @ApiModelProperty("购买数量")
    private Integer quantity;
}