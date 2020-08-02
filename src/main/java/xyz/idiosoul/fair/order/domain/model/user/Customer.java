package xyz.idiosoul.fair.order.domain.model.user;

import lombok.Getter;
import xyz.idiosoul.fair.order.constant.PaymentChannelEnum;
import xyz.idiosoul.fair.order.domain.model.address.ShippingAddress;
import xyz.idiosoul.fair.order.domain.model.order.Order;
import xyz.idiosoul.fair.order.domain.model.payment.Payment;
import xyz.idiosoul.fair.order.repository.AddressRepository;
import xyz.idiosoul.fair.order.repository.PaymentRepository;

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

    private AddressRepository addressRepository;
    private PaymentRepository paymentRepository;


    Customer(int userId,
             AddressRepository addressRepository,

             PaymentRepository paymentRepository) {
        this.userId = userId;
        this.addressRepository = addressRepository;
        this.paymentRepository = paymentRepository;
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

//    /**
//     * 获取购物车
//     *
//     * @return
//     */
//    public ShoppingCart getShoppingCart() {
//        return shoppingCartFactory.getShoppingCart(userId);
//    }
}
