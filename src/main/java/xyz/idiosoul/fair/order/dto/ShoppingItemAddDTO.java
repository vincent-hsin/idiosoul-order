package xyz.idiosoul.fair.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

//@ApiModel("购物项-添加")
@Data
@AllArgsConstructor
public class ShoppingItemAddDTO {
    //    @ApiModelProperty("店铺id") // TODO redundancy 应该已包含商铺信息，但目前的分销商品存在问题
    private Integer shopId;
    //    @ApiModelProperty("商品规格id（skuId）")
    private Long skuId;
    //    @ApiModelProperty("商品数量")
    private Integer quantity;
}
