package xyz.idiosoul.fair.order.application.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.idiosoul.fair.order.application.service.ProductService;
import xyz.idiosoul.fair.order.application.service.ShopService;
import xyz.idiosoul.fair.order.application.service.ShoppingCartService;
import xyz.idiosoul.fair.order.domain.model.cart.ShoppingCart;
import xyz.idiosoul.fair.order.domain.model.user.Customer;
import xyz.idiosoul.fair.order.domain.model.user.CustomerFactory;
import xyz.idiosoul.fair.order.dto.CartAddDTO;
import xyz.idiosoul.fair.order.dto.ShopDTO;
import xyz.idiosoul.fair.order.dto.ShoppingItemAddDTO;
import xyz.idiosoul.fair.order.dto.SkuDetail;

import java.util.List;

/**
 * 购物车服务-实现
 *
 * @author vincent
 */
@Slf4j
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final CustomerFactory customerFactory;
    // FIXME 解耦
    private final ShopService shopService;
    // FIXME 解耦
    private final ProductService ProductService;

    public ShoppingCartServiceImpl(CustomerFactory customerFactory, ShopService shopService, ProductService ProductService) {
        this.customerFactory = customerFactory;
        this.shopService = shopService;
        this.ProductService = ProductService;
    }

    @Override
    @Transactional
    public void add(int buyerId, ShoppingItemAddDTO shoppingItemAddDTO) {
        // 获取店铺信息
        int shopId = shoppingItemAddDTO.getShopId();
        ShopDTO shopDetail = shopService.getShopDetail(shopId);

//        // 获取商品信息
//        long productId = shoppingItemAddDTO.getProductId();
//        ProductDTO productDetail = ProductService.getDetail(productId);

        // 获取sku信息
        long skuId = shoppingItemAddDTO.getSkuId();
        SkuDetail fairSkuDetail =
                ProductService.getSkuDetail(skuId);

//        // 图片
//        String skuImage = StringUtils.isBlank(fairSkuDetail.getSkuImage()) ? productDetail.getProductImage() :
//                fairSkuDetail.getSkuImage();

        Customer customer = customerFactory.getCustomer(buyerId);
        CartAddDTO cartAddDTO = new CartAddDTO(buyerId, shopId, shopDetail.getShopName(), skuId,
                fairSkuDetail.getSpecificationName(), fairSkuDetail.getSpecificationValue()
                , shoppingItemAddDTO.getQuantity(), fairSkuDetail.getSingleBuyUnitPrice());
        ShoppingCart shoppingCart = customer.getShoppingCart();
        shoppingCart.addShoppingItem(cartAddDTO);
    }

    @Override
    public int getCartItemCount(int buyerId) {
        Customer customer = customerFactory.getCustomer(buyerId);
        ShoppingCart shoppingCart = customer.getShoppingCart();
        return shoppingCart.countShoppingItems();
    }

    @Override
    @Transactional
    public void editQuantity(int buyerId, int shoppingItemId, int quantity) {
        Customer customer = customerFactory.getCustomer(buyerId);
        ShoppingCart shoppingCart = customer.getShoppingCart();
        shoppingCart.editShoppingItemQuantity(shoppingItemId, quantity);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void editSpecificationId(int buyerId, int shoppingItemId, int specificationId) {
        // 取规格信息
        SkuDetail fairSkuDetail = ProductService.getSkuDetail(specificationId);
        String specificationName = fairSkuDetail.getSpecificationName();
        String specificationValue = fairSkuDetail.getSpecificationValue();

        Customer customer = customerFactory.getCustomer(buyerId);
        ShoppingCart shoppingCart = customer.getShoppingCart();
        shoppingCart.editShoppingItemSku(shoppingItemId, specificationId);
    }


    @Override
    @Transactional
    public void deleteCartItems(int buyerId, List<Long> orderItemIds) {
        Customer customer = customerFactory.getCustomer(buyerId);
        ShoppingCart shoppingCart = customer.getShoppingCart();
        shoppingCart.deleteShoppingItems(orderItemIds);
    }
}
