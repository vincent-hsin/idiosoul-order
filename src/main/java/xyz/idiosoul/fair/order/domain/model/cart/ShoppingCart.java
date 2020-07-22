/*
 * 2016-2018 江西侬农网科技有限公司。ALL Rights Reserved
 */

package xyz.idiosoul.fair.order.domain.model.cart;

import lombok.Getter;

import java.util.List;

public class ShoppingCart {
    @Getter
    private List<ShoppingGroup> shoppingGroups;

    public ShoppingCart(List<ShoppingGroup> shoppingGroups) {
        this.shoppingGroups = shoppingGroups;
    }

    public
}
