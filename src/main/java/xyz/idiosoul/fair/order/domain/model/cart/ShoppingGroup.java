package xyz.idiosoul.fair.order.domain.model.cart;

import lombok.Getter;
import xyz.idiosoul.fair.order.domain.model.order.Order;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 购物车购物组（domain model）
 */
@Getter
@Entity
@DiscriminatorValue("10") // OrderTypeEnum.SC
public class ShoppingGroup extends Order {
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    protected List<ShoppingItem> shoppingItems;

    protected Integer buyerId, sellerId;
    protected String buyerName, sellerName;

    protected Integer platformId;
    protected Integer dataSpace;
    protected String clientChannel;

    protected ShoppingGroup() {
        // for Hibernate
    }

    public ShoppingGroup(Integer buyerId, Integer sellerId,
                         String sellerName, Integer platformId,
                         Integer dataSpace, String clientChannel) {
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.sellerName = sellerName;
        this.platformId = platformId;
        this.dataSpace = dataSpace;
        this.clientChannel = clientChannel;
//        this.type = OrderTypeEnum.SC.getValue();
    }

    public void add(ShoppingItem shoppingItem) {
        if (Objects.isNull(shoppingItems)) {
            this.shoppingItems = new ArrayList<>();
        }
        Optional<ShoppingItem> shoppingItemOptional =
                shoppingItems.stream().filter(si -> si.getProductId().equals(shoppingItem.getProductId()) && si.getSkuId().equals(shoppingItem.getSkuId()) && si.isDeleted() == false).findFirst();
        if (shoppingItemOptional.isPresent()) {
            shoppingItemOptional.get().addQuantity(shoppingItem.getQuantity());
        } else {
            shoppingItems.add(shoppingItem);
        }
    }

    /**
     * 获取购物项件数
     *
     * @return
     */
    public Integer getPieceCount() {
        return shoppingItems.stream().mapToInt(ShoppingItem::getQuantity).sum();
    }

    public Integer getItemCount() {
        return shoppingItems.size();
    }

    public List<ShoppingItem> getShoppingItems() {
        return shoppingItems.stream().filter(shoppingItem -> shoppingItem.isDeleted() == false).collect(Collectors.toList());
    }
}
