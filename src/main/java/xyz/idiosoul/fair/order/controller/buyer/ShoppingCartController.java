package xyz.idiosoul.fair.order.controller.buyer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.idiosoul.fair.order.application.service.ShoppingCartService;
import xyz.idiosoul.fair.order.dto.ShoppingItemAddDTO;
import xyz.idiosoul.fair.order.util.RequestHeaderUtil;

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
//    private final ShoppingCartMapper shoppingCartMapper;
//    private final PromotionService promotionService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
//        this.shoppingCartMapper = shoppingCartMapper;
//        this.promotionService = promotionService;
    }

//    @ApiOperation("加入购物车")
    @PostMapping("item")
    public void addItem(@RequestBody ShoppingItemAddDTO shoppingItemAddDTO) {
        int buyerId = RequestHeaderUtil.getBuyerId();
        shoppingCartService.add(buyerId, shoppingItemAddDTO);
    }

////    @ApiOperation("查看购物车")
//    @GetMapping("items")
//    public List<ShoppingGroupVO> viewItems() {
//        int buyerId = RequestHeaderUtil.getBuyerId();
//        int dataSpace = RequestHeaderUtil.getDataSpace();
//        // 先刷新购物项价格
//        // todo 暂时关闭
//        shoppingCartService.refreshPrice(buyerId, dataSpace);
//        List<OrderOfShopVO> orderOfShops = shoppingCartMapper.getShoppingCartByBuyerIdAndDataSpace(buyerId, dataSpace);
//
//        List<ShoppingGroupVO> shoppingGroups = new ArrayList<>();
//        for (OrderOfShopVO orderOfShop : orderOfShops) {
//            ShoppingGroupVO shoppingGroup = new ShoppingGroupVO();
//            shoppingGroups.add(shoppingGroup);
//            shoppingGroup.setShopId(orderOfShop.getShopId());
//            shoppingGroup.setShopName(orderOfShop.getShopName());
//            Map<PriceBreakRule, List<ShoppingItemVO>> priceBreakRuleListMap = new HashMap<>();
//            List<ShoppingSubgroupVO> shoppingSubgroups = new ArrayList<>();
//            shoppingGroup.setShoppingSubgroups(shoppingSubgroups);
//            for (ShoppingItemVO shoppingItem : orderOfShop.getOrderItems()) {
//                String promotionTypes = Optional.ofNullable(shoppingItem.getPromotionTypes()).orElse("");
//                // 满减优惠
//                if (promotionTypes.contains(FairPromotionTypeEnum.PRICE_BREAK_DISCOUNT.name())) {
//                    // 获取满减规则
//                    PriceBreakRule priceBreakRule =
//                            promotionService.getPriceBreakRuleDetail(shoppingItem.getProductId());
//
//                    if (priceBreakRuleListMap.containsKey(priceBreakRule)) {
//                        priceBreakRuleListMap.get(priceBreakRule).add(shoppingItem);
//                    } else {
//                        List<ShoppingItemVO> shoppingItems = new ArrayList();
//                        shoppingItems.add(shoppingItem);
//                        priceBreakRuleListMap.put(priceBreakRule, shoppingItems);
//                    }
//                } else {
//                    if (priceBreakRuleListMap.containsKey(null)) {
//                        priceBreakRuleListMap.get(null).add(shoppingItem);
//                    } else {
//                        List<ShoppingItemVO> shoppingItems = new ArrayList();
//                        shoppingItems.add(shoppingItem);
//                        priceBreakRuleListMap.put(null, shoppingItems);
//                    }
//                }
//
//            }
//            for (Map.Entry<PriceBreakRule, List<ShoppingItemVO>> priceBreakRuleListEntry :
//                    priceBreakRuleListMap.entrySet()) {
//                ShoppingSubgroupVO shoppingSubgroup = new ShoppingSubgroupVO();
//                PriceBreakRule key = priceBreakRuleListEntry.getKey();
//                List<ShoppingItemVO> value = priceBreakRuleListEntry.getValue();
//                if (Objects.nonNull(key)) {
//                    shoppingSubgroup.setPriceBreakRule(priceBreakRuleListEntry.getKey().toString());
//                    shoppingSubgroup.setPriceBreakType(priceBreakRuleListEntry.getKey().getPriceBreakType());
//                }
//                shoppingSubgroup.setOrderItems(value);
//                shoppingSubgroups.add(shoppingSubgroup);
//            }
//        }
//        orderOfShops.stream().flatMap(orderOfShopVO -> orderOfShopVO.getOrderItems().stream()).forEach(shoppingItemVO -> {
//            String promotionTypes = shoppingItemVO.getPromotionTypes();
//            // 有促销活动
//            if (Objects.nonNull(promotionTypes)) {
//                if (promotionTypes.contains(FairPromotionTypeEnum.LIMITED_TIME.name())) {
//                    // todo 显示倒计时
//                }
//
//                if (promotionTypes.contains(FairPromotionTypeEnum.PRICE_BREAK_DISCOUNT.name())) {
//                    // todo 满减
//                    PriceBreakRule priceBreakRule =
//                            promotionService.getPriceBreakRuleDetail(shoppingItemVO.getProductId());
////                    shoppingItemVO.setPriceBreakDiscountRule(priceBreakRule.toString());
//                }
//
//                // 团购
//                if (promotionTypes.contains(FairPromotionTypeEnum.GROUP_BUY.name())) {
//                    shoppingItemVO.setGroupBuyPrice(promotionService.getGroupPromotionPrice(shoppingItemVO.getSpecificationId()));
//                }
//            }
//        });
//        return shoppingGroups;
//    }

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
