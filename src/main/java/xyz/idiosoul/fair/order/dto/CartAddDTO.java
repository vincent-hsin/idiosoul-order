/*
 * 2016-2018 江西侬农网科技有限公司。ALL Rights Reserved
 */

package xyz.idiosoul.fair.order.dto;

//import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CartAddDTO {
    private Integer buyerId;
//    @ApiModelProperty("店铺id")
    private Integer shopId;
    private String shopName;

//    @ApiModelProperty("商品id")
//    private Long productId;
//    @ApiModelProperty("商品名称")
//    private String productName;
//    @ApiModelProperty("商品图片")
//    private String productImage;
//    @ApiModelProperty("商品规格id")
    private Long specificationId;
//    @ApiModelProperty("规格名称")
    private String specificationName;
//    @ApiModelProperty("规格值")
    private String specificationValue;
//    @ApiModelProperty("商品数量")
    private int quantity;
//    @ApiModelProperty("单价")
    private BigDecimal unitPrice;

    private int platformId;
    private Integer dataSpace;
    private String clientChannel;

    public CartAddDTO(int buyerId,
//                      Long productId,
//                      String productName,
//                      String productImage,
                      Long specificationId, String specificationName,
                      String specificationValue, Integer quantity, BigDecimal unitPrice) {
        this.buyerId = buyerId;
//        this.shopId = shopId;
//        this.shopName = shopName;
//        this.productId = productId;
//        this.productName = productName;
//        this.productImage = productImage;
        this.specificationId = specificationId;
        this.specificationName = specificationName;
        this.specificationValue = specificationValue;
        this.quantity = quantity;
        this.unitPrice = unitPrice;

        this.platformId = platformId;
        this.dataSpace = dataSpace;
        this.clientChannel = clientChannel;
    }
}
