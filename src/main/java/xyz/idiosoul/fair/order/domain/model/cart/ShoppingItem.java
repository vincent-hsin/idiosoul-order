/*
 * 2016-2018 江西侬农网科技有限公司。ALL Rights Reserved
 */

/*
 * 2016-2018 江西侬农网科技有限公司。ALL Rights Reserved
 */

/*
 * 2016-2018 江西侬农网科技有限公司。ALL Rights Reserved
 */
package xyz.idiosoul.fair.order.domain.model.cart;


import lombok.Getter;
import lombok.ToString;
import xyz.idiosoul.fair.order.infrastructure.EntityBase;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@ToString
@Entity
public class ShoppingItem extends EntityBase<Long> {
    private Long productId;
    private String productName;
    private Long skuId;
    private String specificationName;
    private String specificationValue;
    private String productImage;
    private BigDecimal unitPrice;
    private Integer quantity;

    private String statusView;
    private String promotionTypes;

    protected ShoppingItem() {
        // for Hibernate
    }

    public ShoppingItem(Long productId, String productName, Long skuId,
                        String specificationName,
                        String specificationValue, String productImage, BigDecimal unitPrice, Integer quantity) {
        this.productId = productId;
        this.productName = productName;
        this.skuId = skuId;
        this.specificationName = specificationName;
        this.specificationValue = specificationValue;
        this.productImage = productImage;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.createTime = LocalDateTime.now();
    }

    public void clearPromotionTypes() {
        this.promotionTypes = null;
    }

    public void delete() {
        this.deleted = true;
    }

    public void editSpecification(long specificationId, String specificationName, String specificationValue) {
        this.skuId = specificationId;
        this.specificationName = specificationName;
        this.specificationValue = specificationValue;
        this.modifyTime = LocalDateTime.now();
    }

    public void editQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    public void updateUnitPrice(BigDecimal unitPrice) {
        if (!this.unitPrice.equals(unitPrice)) {
            this.unitPrice = unitPrice;
        }
    }
}
