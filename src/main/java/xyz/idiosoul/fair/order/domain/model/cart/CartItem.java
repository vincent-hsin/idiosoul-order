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

/**
 * 购物项 Entity
 */
@Getter
@ToString
@Entity
public class CartItem extends EntityBase<Long> {
    private Long skuId;
    private Integer quantity;

    protected CartItem() {
        // for Hibernate
    }

    public CartItem(Long skuId) {
        this.skuId = skuId;
        this.quantity = 0;
        this.createTime = LocalDateTime.now();
    }

    public CartItem(Long skuId, Integer quantity) {
        this.skuId = skuId;
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

}
