package xyz.idiosoul.fair.order.domain.model.cart;

import xyz.idiosoul.fair.order.dto.CartAddDTO;
import xyz.idiosoul.fair.order.dto.ShoppingItemAddDTO;

import java.util.List;

public class ShoppingCart {
    private ShoppingGroupFactory shoppingGroupFactory;
    private List<ShoppingGroup> shoppingGroups;

    public ShoppingCart(ShoppingGroupFactory shoppingGroupFactory, List<ShoppingGroup> shoppingGroups) {
        this.shoppingGroupFactory = shoppingGroupFactory;
        this.shoppingGroups = shoppingGroups;
    }

    public void addShoppingItem(CartAddDTO cartAddDTO) {
        int buyerId = cartAddDTO.getBuyerId();
        int sellerId = cartAddDTO.getShopId();
        ShoppingGroup shoppingGroup = shoppingGroups.stream().filter(sg -> sg.getSellerId() == cartAddDTO.getShopId()).findAny().orElse(shoppingGroupFactory.createShoppingGroup(buyerId, sellerId));
        shoppingGroup.add(new ShoppingItemAddDTO(cartAddDTO.getShopId(),
                cartAddDTO.getSpecificationId(),
                cartAddDTO.getQuantity()));
    }

    public void editShoppingItemQuantity(int shoppingItemId, int quantity){
        ShoppingItem shoppingItem = getShoppingItem(shoppingItemId);
        shoppingItem.editQuantity(quantity);
    }

    public void editShoppingItemSku(int shoppingItemId, int skuId){
        ShoppingItem shoppingItem = getShoppingItem(shoppingItemId);
        shoppingItem.editSpecification(skuId);
    }

    public void deleteShoppingItems(List<Long> shoppingItemIds) {
        shoppingGroups.stream().flatMap(shoppingGroup -> shoppingGroup.getShoppingItems().stream()).filter(shoppingItem -> shoppingItemIds.contains(shoppingItem.getId())).forEach(shoppingItem -> shoppingItem.delete());
    }

    public ShoppingItem getShoppingItem(long shoppingItemId) {
        return shoppingGroups.stream().flatMap(shoppingGroup -> shoppingGroup.getShoppingItems().stream()).filter(shoppingItem -> shoppingItem.getId() == shoppingItemId).findAny().orElseThrow(() -> new RuntimeException("购物项不存在"));
    }

    public int countShoppingItems() {
        return shoppingGroups.stream().mapToInt(ShoppingGroup::getItemCount).sum();
    }
}
