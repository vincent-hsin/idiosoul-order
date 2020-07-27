package xyz.idiosoul.fair.order.domain.model.cart;

import xyz.idiosoul.fair.order.dto.CartAddDTO;
import xyz.idiosoul.fair.order.dto.ShoppingItemAddDTO;
import xyz.idiosoul.fair.order.dto.ShoppingItemDTO;

import java.util.List;
import java.util.Map;
import java.util.Set;

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

    public void editShoppingItemQuantity(int sellerId, int skuId, int quantity) {
        ShoppingItem shoppingItem = getShoppingItem(sellerId, skuId);
        shoppingItem.editQuantity(quantity);
    }

    public void editShoppingItemSku(int sellerId, int sourceSkuId, int targetSkuId) {
        ShoppingItem shoppingItem = getShoppingItem(sellerId,sourceSkuId);
        shoppingItem.editSpecification(targetSkuId);
    }

    public void deleteShoppingItems(Map<Integer, Set<Integer>> shoppingMap) {
        shoppingMap.entrySet().forEach(shoppingItems->{
            shoppingGroups.stream().filter(shoppingGroup -> shoppingGroup.getSellerId() == shoppingItems.getKey()).findAny().orElseThrow(()->new RuntimeException("购物项不存在")).getShoppingItems().stream().forEach(shoppingItem -> {
                if(shoppingItems.getValue().contains(shoppingItem.getSkuId() )){
                    shoppingItem.delete();
                }
            });
        });
//        shoppingGroups.stream().flatMap(shoppingGroup -> shoppingGroup.getShoppingItems().stream()).filter(shoppingItem -> shoppingItemIds.contains(shoppingItem.getId())).forEach(shoppingItem -> shoppingItem.delete());
    }

    public ShoppingItem getShoppingItem(long shoppingItemId) {
        return shoppingGroups.stream().flatMap(shoppingGroup -> shoppingGroup.getShoppingItems().stream()).filter(shoppingItem -> shoppingItem.getId() == shoppingItemId).findAny().orElseThrow(() -> new RuntimeException("购物项不存在"));
    }

    public ShoppingItem getShoppingItem(int sellerId, int skuId) {
        return shoppingGroups.stream().filter(shoppingGroup -> shoppingGroup.getSellerId() == sellerId).findAny().orElseThrow(() -> new RuntimeException("购物项不存在")).getShoppingItems().stream().filter(shoppingItem -> shoppingItem.getSkuId() == skuId).findAny().orElseThrow(() -> new RuntimeException("购物项不存在"));
    }

    public int countShoppingItems() {
        return shoppingGroups.stream().mapToInt(ShoppingGroup::getItemCount).sum();
    }
}
