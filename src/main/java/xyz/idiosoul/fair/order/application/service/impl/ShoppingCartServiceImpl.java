package xyz.idiosoul.fair.order.application.service.impl;

import io.micrometer.core.instrument.util.StringUtils;
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
import xyz.idiosoul.fair.order.dto.ProductDTO;
import xyz.idiosoul.fair.order.dto.ShopDTO;
import xyz.idiosoul.fair.order.dto.ShoppingItemAddDTO;
import xyz.idiosoul.fair.order.dto.SkuDetail;

import java.util.List;

@Slf4j
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private CustomerFactory customerFactory;
    @Autowired
    private ShopService shopService;
    @Autowired
    private ProductService fairProductService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(int buyerId, ShoppingItemAddDTO shoppingItemAddDTO) {
        // 获取店铺信息
        int shopId = shoppingItemAddDTO.getShopId();
        ShopDTO shopDetail = shopService.getShopDetail(shopId);

        // 获取商品信息
        long productId = shoppingItemAddDTO.getProductId();
        ProductDTO productDetail = fairProductService.getDetail(productId);

        // 获取sku信息
        long specificationId = shoppingItemAddDTO.getSpecificationId();
        SkuDetail fairSkuDetail =
                fairProductService.getSkuDetail(specificationId);

        // 图片
        String skuImage = StringUtils.isBlank(fairSkuDetail.getSkuImage()) ? productDetail.getProductImage() :
                fairSkuDetail.getSkuImage();

        Customer customer = customerFactory.getCustomer(buyerId);
        CartAddDTO cartAddDTO = new CartAddDTO(buyerId, shopId, shopDetail.getShopName(), productId,
                productDetail.getProductName(), skuImage, specificationId,
                fairSkuDetail.getSpecificationName(), fairSkuDetail.getSpecificationValue()
                , shoppingItemAddDTO.getQuantity(), fairSkuDetail.getSingleBuyUnitPrice());
        customer.addCartItem(cartAddDTO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void refreshPrice(int buyerId, int dataSpace) {
        Customer customer = customerFactory.getCustomer(buyerId);
        ShoppingCart shoppingCart = customer.getShoppingCart(dataSpace);
        shoppingCart.getShoppingGroups().stream().flatMap(shoppingGroup -> shoppingGroup.getShoppingItems().stream()).forEach(shoppingItem -> {

            // todo 1.判断失效商品 2.判断价格变化
            // 获取sku信息
            SkuDetail fairSkuDetail = fairProductService.getSkuDetail(shoppingItem.getSkuId());

//            // 检查库存状态
//            if (shoppingItem.getStatusView().equals(ShoppingItemStatusViewEnum.VALID.toString()) &&
//                    fairSkuDetail.getInventory() < shoppingItem.getQuantity()) {
//                shoppingItem.updateStatusView(ShoppingItemStatusViewEnum.OUT_OF_STOCK);
//                return;
//            }
//            if (shoppingItem.getStatusView().equals(ShoppingItemStatusViewEnum.OUT_OF_STOCK.toString()) &&
//                    fairSkuDetail.getInventory() >= shoppingItem.getQuantity()) {
//                shoppingItem.updateStatusView(ShoppingItemStatusViewEnum.VALID);
//            }

            // 更新单价
            shoppingItem.updateUnitPrice(fairSkuDetail.getSingleBuyUnitPrice());

            /*================= 促销类型 ==============*/

            // 先清空
            shoppingItem.clearPromotionTypes();
        });
    }

    @Override
    public int getCartItemCount(int buyerId) {
        Customer customer = customerFactory.getCustomer(buyerId);
        return customer.getCartItemCount();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void editQuantity(int buyerId, int shoppingItemId, int quantity) {
        Customer customer = customerFactory.getCustomer(buyerId);
        customer.editCartItemQuantity(shoppingItemId, quantity);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void editSpecificationId(int buyerId, int shoppingItemId, int specificationId) {
        // 取规格信息
        SkuDetail fairSkuDetail = fairProductService.getSkuDetail(specificationId);
        String specificationName = fairSkuDetail.getSpecificationName();
        String specificationValue = fairSkuDetail.getSpecificationValue();

        Customer customer = customerFactory.getCustomer(buyerId);

        customer.editCartItemSpecification(shoppingItemId, specificationId, specificationName, specificationValue);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(int buyerId, List<Long> orderItemIds) {
        Customer customer = customerFactory.getCustomer(buyerId);
        customer.deleteCartItem(orderItemIds);
    }
}
