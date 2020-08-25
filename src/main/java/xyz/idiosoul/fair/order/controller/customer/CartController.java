package xyz.idiosoul.fair.order.controller.customer;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.idiosoul.fair.order.application.service.CartService;
import xyz.idiosoul.fair.order.dao.CartMapper;
import xyz.idiosoul.fair.order.dto.CartItemAddDTO;
import xyz.idiosoul.fair.order.util.RequestHeaderUtil;
import xyz.idiosoul.fair.order.vo.ShoppingGroupOfShopVO;

import java.util.List;
import java.util.Set;

//@Api(tags = "顾客-购物车")
@RestController
@RequestMapping("api/v1.0/customer/cart")
public class CartController {
    private final CartService cartService;
    private final CartMapper cartMapper;

    public CartController(CartService cartService, CartMapper cartMapper) {
        this.cartService = cartService;
        this.cartMapper = cartMapper;
    }

    //    @ApiOperation("加入购物车")
    @PostMapping("item")
    public void addItem(@RequestBody CartItemAddDTO cartItemAddDTO) {
        int buyerId = RequestHeaderUtil.getCustomerId();
        cartService.add(buyerId, cartItemAddDTO);
    }

    //    @ApiOperation("查看购物车")
    @GetMapping
    public List<ShoppingGroupOfShopVO> viewItems() {
        int customerId = RequestHeaderUtil.getCustomerId();
        return cartMapper.getShoppingCartByBuyerIdAndDataSpace(customerId);

    }

    //    @ApiOperation("获取购物车中购物项数量")
    @GetMapping("items/count")
    public int itemsCount() {
        int customerId = RequestHeaderUtil.getCustomerId();
        return cartService.getCartItemsCount(customerId);
    }

    //    @ApiOperation("修改购物车商品数量")
    @PutMapping("item/quantity")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "shoppingItemId", value = "购物项id"),
//            @ApiImplicitParam(name = "quantity", value = "商品数量"),
//    })
    public void editQuantity(int skuId, int quantity) {
        int customerId = RequestHeaderUtil.getCustomerId();
        cartService.editQuantity(customerId, skuId, quantity);
    }

    //    @ApiOperation("修改购物车商品规格")
    @PutMapping("item/sku")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "shoppingItemId", value = "购物项id"),
//            @ApiImplicitParam(name = "specificationId", value = "规格id")
//    })
    public void editSpecificationId(int sourceSkuId, int targetSkuId) {
        int customerId = RequestHeaderUtil.getCustomerId();
        cartService.editSkuId(customerId, sourceSkuId, targetSkuId);
    }

    //    @ApiOperation("删除购物车商品")
    @DeleteMapping("items")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "orderItemIds", value = "购物项id（数组）,例如：[1,2,3]")
//    })
    public void deleteItems(@RequestBody Set<Long> cartItems) {
        int customerId = RequestHeaderUtil.getCustomerId();
        cartService.deleteCartItems(customerId, cartItems);
    }
}
