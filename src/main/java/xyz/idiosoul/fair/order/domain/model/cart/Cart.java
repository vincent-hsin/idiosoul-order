package xyz.idiosoul.fair.order.domain.model.cart;

import lombok.Getter;
import xyz.idiosoul.fair.order.dto.CartItemAddDTO;
import xyz.idiosoul.fair.order.infrastructure.EntityBase;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 购物车 Entity
 */
@Getter
@Entity
public class Cart extends EntityBase<Long> {
    private Integer customerId;
    protected String buyerName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_id")
    private List<CartItem> cartItems;

    protected Cart() {
        // for Hibernate
    }

    public Cart(Integer customerId) {
        this.customerId = customerId;
    }

    public void add(CartItemAddDTO cartItemAddDTO) {
        if (cartItems == null) {
            this.cartItems = new ArrayList<>();
        }
        CartItem cartItem =
                cartItems.stream().filter(si -> si.getSkuId().equals(cartItemAddDTO.getSkuId()) && si.isDeleted() == false).findAny().orElse(new CartItem(cartItemAddDTO.getSkuId()));
        cartItem.addQuantity(cartItemAddDTO.getQuantity());
        cartItems.add(cartItem);
    }

    /**
     * 获取购物项件数
     *
     * @return
     */
    public Integer getPieceCount() {
        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }
        return cartItems.stream().mapToInt(CartItem::getQuantity).sum();
    }

    public Integer countItems() {
        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }
        return cartItems.size();
    }

    public List<CartItem> getCartItems() {
        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }
        return cartItems.stream().filter(cartItem -> cartItem.isDeleted() == false).collect(Collectors.toList());
    }

    private CartItem getCartItem(int skuId) {
        return cartItems.stream().filter(cartItem -> cartItem.getSkuId() == skuId).findAny().orElseThrow(() -> new RuntimeException("购物项不存在"));
    }

    public void editItemQuantity(int skuId, int quantity) {
        CartItem cartItem = getCartItem(skuId);
        cartItem.editQuantity(quantity);
    }

    public void editItemSku(int sourceSkuId, int targetSkuId) {
        CartItem cartItem = getCartItem(sourceSkuId);
        cartItem.editSpecification(targetSkuId);
    }

    public void delete(Set<Long> skuIds) {
        this.cartItems.stream().forEach(cartItem -> {
            if (skuIds.contains(cartItem.getSkuId())) {
                cartItem.delete();
            }
        });
    }
}
