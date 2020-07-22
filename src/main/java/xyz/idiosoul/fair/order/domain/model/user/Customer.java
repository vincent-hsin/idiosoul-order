package xyz.idiosoul.fair.order.domain.model.user;

import lombok.Getter;
import xyz.idiosoul.fair.order.constant.PaymentChannelEnum;
import xyz.idiosoul.fair.order.domain.model.address.ShippingAddress;
import xyz.idiosoul.fair.order.domain.model.cart.ShoppingCart;
import xyz.idiosoul.fair.order.domain.model.cart.ShoppingCartFactory;
import xyz.idiosoul.fair.order.domain.model.cart.ShoppingGroup;
import xyz.idiosoul.fair.order.domain.model.cart.ShoppingGroupFactory;
import xyz.idiosoul.fair.order.domain.model.cart.ShoppingItem;
import xyz.idiosoul.fair.order.domain.model.order.LineItem;
import xyz.idiosoul.fair.order.domain.model.order.Order;
import xyz.idiosoul.fair.order.domain.model.payment.Payment;
import xyz.idiosoul.fair.order.dto.CartAddDTO;
import xyz.idiosoul.fair.order.dto.ShoppingItemAddDTO;
import xyz.idiosoul.fair.order.infrastructure.service.NumberGenerator;
import xyz.idiosoul.fair.order.repository.AddressRepository;
import xyz.idiosoul.fair.order.repository.LineItemRepository;
import xyz.idiosoul.fair.order.repository.PaymentRepository;
import xyz.idiosoul.fair.order.repository.RequestEventRepository;
import xyz.idiosoul.fair.order.repository.RequestRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 顾客 domain model
 */
public class Customer {
    @Getter
    private int userId;
    @Getter
    private String userName;

    private LineItemRepository lineItemRepository;
    private AddressRepository addressRepository;
    private RequestRepository requestRepository;
    private RequestEventRepository requestEventRepository;
    private PaymentRepository paymentRepository;
    private ShoppingGroupFactory shoppingGroupFactory;
    private NumberGenerator numberGenerator;
    private ShoppingCartFactory shoppingCartFactory;


    Customer(int userId, LineItemRepository lineItemRepository,
             AddressRepository addressRepository, RequestRepository requestRepository,
             RequestEventRepository requestEventRepository,
             PaymentRepository paymentRepository,
             ShoppingGroupFactory shoppingGroupFactory,
             NumberGenerator numberGenerator, ShoppingCartFactory shoppingCartFactory) {
        this.userId = userId;
        this.lineItemRepository = lineItemRepository;
        this.addressRepository = addressRepository;
        this.requestRepository = requestRepository;
        this.requestEventRepository = requestEventRepository;
        this.paymentRepository = paymentRepository;
        this.shoppingGroupFactory = shoppingGroupFactory;
        this.numberGenerator = numberGenerator;
        this.shoppingCartFactory = shoppingCartFactory;
    }

    /**
     * 根据id获取地址
     *
     * @param addressId
     * @return
     */
    public ShippingAddress getShippingAddress(int addressId) {
        return addressRepository.findOptionalByUserIdAndIdAndDeletedIsFalse(userId, addressId).orElseThrow(() -> new RuntimeException("地址不存在"));
    }

    /**
     * 获取默认地址
     *
     * @return
     */
    public ShippingAddress getDefaultShippingAddress() {
        return addressRepository.findByUserIdAndIsDefaultIsTrueAndDeletedIsFalse(userId);
    }


    public Payment createPayment(List<Order> orders, PaymentChannelEnum paymentChannel) {
        LocalDateTime now = LocalDateTime.now();
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (Order order : orders) {
            totalAmount = totalAmount.add(order.getTotalAmount());
        }
        Payment payment = new Payment(paymentChannel.getValue(), totalAmount, userId);
        paymentRepository.save(payment);
        orders.forEach(order -> order.setPaymentId(payment.getId())); //todo many2one
        return payment;
    }

    public Payment getPayment(long paymentId) {
        return paymentRepository.findById(paymentId).filter(payment -> payment.getCreateUserId() == userId).orElseThrow(() -> new RuntimeException("支付记录不存在"));

    }

    /**
     * 获取购物车
     *
     * @return
     */
    public ShoppingCart getShoppingCart() {
        return shoppingCartFactory.getShoppingCart(userId);
    }

    // === 购物车相关 ==== //
    public void addCartItem(CartAddDTO cartAddDTO) {
//        // todo 为什么 shoppingCartRepository 订单类型过滤不掉
//        Order order =
//                orderRepository.findFirstByBuyerIdAndSellerIdAndTypeAndDeletedIsFalse(userId,
//                        cartAddDTO.getShopId(), OrderTypeEnum.SC.getValue());
//        if (Objects.nonNull(order)) {
//            shoppingGroup =
//                    shoppingGroupRepository.findById(order.getId()).orElseThrow(() -> new RuntimeException(
//                            "购物车数据异常"));
//        } else {
        Integer buyerId = cartAddDTO.getBuyerId();
        Integer sellerId = cartAddDTO.getShopId();
        ShoppingGroup shoppingGroup = shoppingGroupFactory.getShoppingGroup(buyerId, sellerId).orElse(shoppingGroupFactory.createShoppingGroup(buyerId, sellerId));
//        }

        shoppingGroup.add(new ShoppingItemAddDTO(cartAddDTO.getShopId(),
                cartAddDTO.getSpecificationId(),
                cartAddDTO.getQuantity()));
    }

    /**
     * 编辑购物项数量
     *
     * @param shoppingItemId
     * @param quantity
     */
    public void editCartItemQuantity(int shoppingItemId, int quantity) {
        ShoppingItem shoppingItem = getShoppingItem(shoppingItemId);
        shoppingItem.editQuantity(quantity);
    }

    public void editCartItemSpecification(int shoppingItemId, int specificationId, String specificationName,
                                          String specificationValue) {
        ShoppingItem shoppingItem = getShoppingItem(shoppingItemId);
        shoppingItem.editSpecification(specificationId, specificationName, specificationValue);
    }

    private ShoppingItem getShoppingItem(int shoppingItemId) {
        ShoppingCart shoppingCart = getShoppingCart();
        return shoppingCart.getShoppingGroups().stream().flatMap(shoppingGroup -> shoppingGroup.getShoppingItems().stream()).filter(shoppingItem -> shoppingItem.getId() == shoppingItemId).findFirst().orElseThrow(() -> new RuntimeException("购物项不存在"));
    }

    /**
     * 获取购物项数量
     *
     * @return
     */
    public int getCartItemCount() {
        List<ShoppingGroup> shoppingGroups =
                shoppingGroupRepository.findAllByBuyerIdAndDeletedFalse(userId);
        return shoppingGroups.stream().mapToInt(ShoppingGroup::getItemCount).sum();
    }

    public void deleteCartItem(List<Long> shoppingItemIds) {
        List<ShoppingGroup> shoppingGroups =
                shoppingGroupRepository.findAllByBuyerIdAndDeletedFalse(userId);
        shoppingGroups.stream().flatMap(shoppingGroup -> shoppingGroup.getShoppingItems().stream()).filter(shoppingItem -> shoppingItemIds.contains(shoppingItem.getId())).forEach(shoppingItem -> shoppingItem.delete());
    }

    /**
     * 获取购物项
     *
     * @return
     */
    public LineItem getShoppingItem(long shoppingItemId) {
        return lineItemRepository.findById(shoppingItemId).orElseThrow(() -> new RuntimeException(
                "购物项不存在"));
    }
}
