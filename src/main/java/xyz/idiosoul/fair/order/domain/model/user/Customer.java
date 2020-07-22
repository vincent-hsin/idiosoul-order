package xyz.idiosoul.fair.order.domain.model.user;

import lombok.Getter;
import xyz.idiosoul.fair.order.constant.PaymentChannelEnum;
import xyz.idiosoul.fair.order.domain.model.address.ShippingAddress;
import xyz.idiosoul.fair.order.domain.model.cart.ShoppingCart;
import xyz.idiosoul.fair.order.domain.model.cart.ShoppingCartFactory;
import xyz.idiosoul.fair.order.domain.model.cart.ShoppingGroup;
import xyz.idiosoul.fair.order.domain.model.cart.ShoppingItem;
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
    private NumberGenerator numberGenerator;
    private ShoppingCartFactory shoppingCartFactory;


    Customer(int userId, LineItemRepository lineItemRepository,
             AddressRepository addressRepository, RequestRepository requestRepository,
             RequestEventRepository requestEventRepository,
             PaymentRepository paymentRepository,
             NumberGenerator numberGenerator, ShoppingCartFactory shoppingCartFactory) {
        this.userId = userId;
        this.lineItemRepository = lineItemRepository;
        this.addressRepository = addressRepository;
        this.requestRepository = requestRepository;
        this.requestEventRepository = requestEventRepository;
        this.paymentRepository = paymentRepository;
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
}
