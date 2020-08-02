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
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 购物项 Entity
 */
@Getter
@ToString
@Entity
public class CartItem extends EntityBase<Long> {
    private Long productId;
    private String productName;
    private Long skuId;
    private String specificationName;
    private String specificationValue;
    private String productImage;
    private BigDecimal unitPrice;
    private Integer quantity;

    protected CartItem() {
        // for Hibernate
    }

    public CartItem(Long skuId) {
        this.skuId = skuId;
        this.quantity = 0;
        this.createTime = LocalDateTime.now();
    }

    public CartItem(Long skuId, BigDecimal unitPrice, Integer quantity) {
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

    public void editSpecification(long specificationId) {
        this.skuId = specificationId;
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
