package xyz.idiosoul.fair.order.controller.buyer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.idiosoul.fair.order.application.service.ShoppingCartService;
import xyz.idiosoul.fair.order.dao.ShoppingCartMapper;
import xyz.idiosoul.fair.order.dto.ShoppingGroupVO;
import xyz.idiosoul.fair.order.dto.ShoppingItemAddDTO;
import xyz.idiosoul.fair.order.vo.ShoppingItemVO;
import xyz.idiosoul.fair.order.util.RequestHeaderUtil;
import xyz.idiosoul.fair.order.vo.OrderOfShopVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

//@Api(tags = "顾客-购物车")
@RestController
@RequestMapping("api/v1.0/consumer/shopping_cart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final ShoppingCartMapper shoppingCartMapper;
//    private final PromotionService promotionService;

    public ShoppingCartController(ShoppingCartService shoppingCartService, ShoppingCartMapper shoppingCartMapper) {
        this.shoppingCartService = shoppingCartService;
//        this.promotionService = promotionService;
        this.shoppingCartMapper = shoppingCartMapper;
    }

//    @ApiOperation("加入购物车")
    @PostMapping("item")
    public void addItem(@RequestBody ShoppingItemAddDTO shoppingItemAddDTO) {
        int buyerId = RequestHeaderUtil.getBuyerId();
        shoppingCartService.add(buyerId, shoppingItemAddDTO);
    }

//    @ApiOperation("查看购物车")
    @GetMapping("items")
    public List<OrderOfShopVO> viewItems() {
        int buyerId = RequestHeaderUtil.getBuyerId();
        // 先刷新购物项价格
        // todo 暂时关闭
//        shoppingCartService.refreshPrice(buyerId, dataSpace);
        List<OrderOfShopVO> orderOfShops = shoppingCartMapper.getShoppingCartByBuyerIdAndDataSpace(buyerId);

        return orderOfShops;
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
    public void editQuantity(int shoppingItemId, int quantity) {
        int buyerId = RequestHeaderUtil.getBuyerId();
        shoppingCartService.editQuantity(buyerId, shoppingItemId, quantity);
    }

//    @ApiOperation("修改购物车商品规格")
    @PostMapping("item/specification/edit")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "shoppingItemId", value = "购物项id"),
//            @ApiImplicitParam(name = "specificationId", value = "规格id")
//    })
    public void editSpecificationId(int shoppingItemId, int specificationId) {
        int buyerId = RequestHeaderUtil.getBuyerId();
        shoppingCartService.editSpecificationId(buyerId, shoppingItemId, specificationId);
    }

//    @ApiOperation("删除购物车商品")
//    @PostMapping("items/delete")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "orderItemIds", value = "购物项id（数组）,例如：[1,2,3]")
//    })
    public void delete(@RequestBody List<Long> orderItemIds) {
        int buyerId = RequestHeaderUtil.getBuyerId();
        shoppingCartService.delete(buyerId, orderItemIds);
    }
}
