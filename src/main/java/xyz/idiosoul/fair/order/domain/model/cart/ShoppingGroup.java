package xyz.idiosoul.fair.order.domain.model.cart;

import lombok.Getter;
import xyz.idiosoul.fair.order.dto.ShoppingItemAddDTO;
import xyz.idiosoul.fair.order.infrastructure.EntityBase;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 购物车购物组 Entity
 */
@Getter
@Entity
public class ShoppingGroup extends EntityBase<Long> {
    private Integer buyerId, sellerId;
    protected String buyerName, sellerName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "shopping_group_id")
    private List<ShoppingItem> shoppingItems;

    protected ShoppingGroup() {
        // for Hibernate
    }

    public ShoppingGroup(Integer buyerId, Integer sellerId) {
        this.buyerId = buyerId;
        this.sellerId = sellerId;
    }

    public void add(ShoppingItemAddDTO shoppingItemAddDTO) {
        if (shoppingItems == null) {
            this.shoppingItems = new ArrayList<>();
        }
        ShoppingItem shoppingItem =
                shoppingItems.stream().filter(si -> si.getSkuId().equals(shoppingItemAddDTO.getSkuId()) && si.isDeleted() == false).findAny().orElse(new ShoppingItem(shoppingItemAddDTO.getSkuId()));
        shoppingItem.addQuantity(shoppingItemAddDTO.getQuantity());
        shoppingItems.add(shoppingItem);
    }

    /**
     * 获取购物项件数
     *
     * @return
     */
    public Integer getPieceCount() {
        return shoppingItems.stream().mapToInt(ShoppingItem::getQuantity).sum();
    }

    public Integer countItems() {
        return shoppingItems.size();
    }

    public List<ShoppingItem> getShoppingItems() {
        return shoppingItems.stream().filter(shoppingItem -> shoppingItem.isDeleted() == false).collect(Collectors.toList());
    }
}
