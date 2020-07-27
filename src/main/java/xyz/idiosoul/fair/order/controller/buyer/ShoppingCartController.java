package xyz.idiosoul.fair.order.controller.buyer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.idiosoul.fair.order.application.service.ShoppingCartService;
import xyz.idiosoul.fair.order.dao.ShoppingCartMapper;
import xyz.idiosoul.fair.order.dto.ShoppingItemAddDTO;
import xyz.idiosoul.fair.order.util.RequestHeaderUtil;
import xyz.idiosoul.fair.order.vo.ShoppingGroupOfShopVO;

import java.util.List;
import java.util.Map;
import java.util.Set;

//@Api(tags = "顾客-购物车")
@RestController
@RequestMapping("api/v1.0/consumer/shopping_cart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final ShoppingCartMapper shoppingCartMapper;

    public ShoppingCartController(ShoppingCartService shoppingCartService, ShoppingCartMapper shoppingCartMapper) {
        this.shoppingCartService = shoppingCartService;
        this.shoppingCartMapper = shoppingCartMapper;
    }

    //    @ApiOperation("加入购物车")
    @PostMapping("item")
    public void addItem(@RequestBody ShoppingItemAddDTO shoppingItemAddDTO) {
        int buyerId = RequestHeaderUtil.getBuyerId();
        shoppingCartService.add(buyerId, shoppingItemAddDTO);
    }

    //    @ApiOperation("查看购物车")
    @GetMapping
    public List<ShoppingGroupOfShopVO> viewItems() {
        int buyerId = RequestHeaderUtil.getBuyerId();
        // 先刷新购物项价格
        // todo 暂时关闭
//        shoppingCartService.refreshPrice(buyerId, dataSpace);
        return shoppingCartMapper.getShoppingCartByBuyerIdAndDataSpace(buyerId);

    }

    //    @ApiOperation("获取购物车中购物项数量")
    @GetMapping("items/count")
    public int itemsCount() {
        int buyerId = RequestHeaderUtil.getBuyerId();
        return shoppingCartService.getCartItemCount(buyerId);
    }

    //    @ApiOperation("修改购物车商品数量")
    @PostMapping("item/quantity/edit")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "shoppingItemId", value = "购物项id"),
//            @ApiImplicitParam(name = "quantity", value = "商品数量"),
//    })
    public void editQuantity(int sellerId, int skuId, int quantity) {
        int buyerId = RequestHeaderUtil.getBuyerId();
        shoppingCartService.editQuantity(buyerId, sellerId, skuId, quantity);
    }

    //    @ApiOperation("修改购物车商品规格")
    @PostMapping("item/specification/edit")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "shoppingItemId", value = "购物项id"),
//            @ApiImplicitParam(name = "specificationId", value = "规格id")
//    })
    public void editSpecificationId(int sellerId, int sourceSkuId, int targetSkuId) {
        int buyerId = RequestHeaderUtil.getBuyerId();
        shoppingCartService.editSkuId(buyerId, sellerId, sourceSkuId, targetSkuId);
    }

    //    @ApiOperation("删除购物车商品")
    @PostMapping("items/delete")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "orderItemIds", value = "购物项id（数组）,例如：[1,2,3]")
//    })
    public void deleteItems(@RequestBody Map<Integer, Set<Integer>> shoppingMap) {
        int buyerId = RequestHeaderUtil.getBuyerId();
        shoppingCartService.deleteCartItems(buyerId, shoppingMap);
    }
}
