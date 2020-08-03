package xyz.idiosoul.fair.order.domain.model.order;

import lombok.Getter;
import lombok.Setter;
import xyz.idiosoul.fair.order.constant.OrderStatusEnum;
import xyz.idiosoul.fair.order.domain.model.address.ShippingAddress;
import xyz.idiosoul.fair.order.domain.model.delivery.Delivery;
import xyz.idiosoul.fair.order.infrastructure.EntityBase;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Entity
@Table(name = "order_header")
public class Order extends EntityBase<Long> {
    /* primary */
    protected String number;
    protected Byte status;
    protected Integer customerId, sellerId;
    protected BigDecimal goodsAmount, deliveryCost;
    protected Long couponId, pointsPaymentId, walletPaymentId;
    protected BigDecimal couponDiscountAmount, pointsPaymentAmount, walletPaymentAmount, memberDiscountAmount;
    protected BigDecimal totalAmount;
    @Setter
    protected Long paymentId;

    // 团购
    protected Long parentId;
    protected Integer groupThreshold;

    protected LocalDateTime processDeadline, receiveTime;
    protected String buyerRemark, sellerRemark, platRemark;
    protected Byte starCount;
    protected String receiverAddress, receiverName, receiverMobile; // 配送信息

    /* 发票信息 */
    private Byte invoiceType;
    private Byte invoiceTitleType;
    private String invoiceTitle;
    private String invoiceTaxpayerNo;
    private String invoiceReceiverEmail;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    protected List<LineItem> lineItems = new ArrayList<>();

    /* redundant */
    protected String buyerName, buyerMobile, sellerName;

    protected Order() {
        // for Hibernate
    }

    public Order(int customerId) {
        this.customerId = customerId;
        this.createTime = LocalDateTime.now();
    }

    /**
     * 通用订单
     *
     * @param number
     * @param customerId
     * @param sellerId
     * @param sellerName
     * @param goodsAmount
     * @param deliveryCost
     * @param couponId
     * @param couponDiscountAmount
     * @param walletPaymentId
     * @param walletPaymentAmount
     * @param pointsPaymentId
     * @param pointsPaymentAmount
     * @param processDeadline
     * @param shippingAddress
     * @param invoice
     * @param platformId
     * @param dataSpace
     * @param clientChannel
     */
    protected Order(String number, Integer customerId, Integer sellerId, String sellerName, BigDecimal goodsAmount,
                    BigDecimal deliveryCost, Long couponId, BigDecimal couponDiscountAmount,
                    Long walletPaymentId, BigDecimal walletPaymentAmount, Long pointsPaymentId,
                    BigDecimal pointsPaymentAmount, LocalDateTime processDeadline, ShippingAddress shippingAddress,
                    Invoice invoice, String buyerRemark, Integer platformId, Integer dataSpace, String clientChannel, Long parentId,
                    Integer groupThreshold) {
        this.number = number;
        this.customerId = customerId;
        this.sellerId = sellerId;
        this.sellerName = sellerName;

        this.goodsAmount = goodsAmount;
        this.deliveryCost = deliveryCost;

        this.couponId = couponId;
        this.couponDiscountAmount = couponDiscountAmount;
        this.walletPaymentId = walletPaymentId;
        this.walletPaymentAmount = walletPaymentAmount;
        this.pointsPaymentId = pointsPaymentId;
        this.pointsPaymentAmount = pointsPaymentAmount;

        this.processDeadline = processDeadline;
        this.buyerRemark = buyerRemark;

        this.parentId = parentId;
        this.groupThreshold = groupThreshold;

        if (Objects.nonNull(shippingAddress)) {
            this.receiverName = shippingAddress.getContactName();
            this.receiverMobile = shippingAddress.getContactMobile();
            this.receiverAddress = shippingAddress.getFullAddress();
        }

        if (Objects.nonNull(invoice)) {
            if (Objects.nonNull(invoice.getInvoiceType())) {
                this.invoiceType = invoice.getInvoiceType().getValue();
            }
            if (Objects.nonNull(invoice.getInvoiceTitleType())) {
                this.invoiceTitleType = invoice.getInvoiceTitleType().getValue();
            }
            this.invoiceTitle = invoice.getInvoiceTitle();
            this.invoiceTaxpayerNo = invoice.getInvoiceTaxpayerNo();
            this.invoiceReceiverEmail = invoice.getInvoiceReceiverEmail();
        }

        // 待支付总金额
        refreshTotalAmount();
//        this.totalAmount =
//                goodsAmount.add(deliveryCost).subtract(couponDiscountAmount).subtract(walletPaymentAmount).subtract(pointsPaymentAmount);
        this.status = OrderStatusEnum.SUBMITTED.getValue();
    }

    /**
     * 单独购买订单
     *
     * @param number
     * @param customerId
     * @param sellerId
     * @param sellerName
     * @param goodsAmount
     * @param deliveryCost
     * @param couponId
     * @param couponDiscountAmount
     * @param walletPaymentId
     * @param walletPaymentAmount
     * @param pointsPaymentId
     * @param pointsPaymentAmount
     * @param processDeadline
     * @param shippingAddress
     * @param invoice
     * @param platformId
     * @param dataSpace
     * @param clientChannel
     */
    protected Order(String number, Integer customerId, Integer sellerId,
                    String sellerName, BigDecimal goodsAmount,
                    BigDecimal deliveryCost, Long couponId, BigDecimal couponDiscountAmount,
                    Long walletPaymentId, BigDecimal walletPaymentAmount, Long pointsPaymentId,
                    BigDecimal pointsPaymentAmount, LocalDateTime processDeadline, ShippingAddress shippingAddress,
                    Invoice invoice, String buyerRemark,
                    Integer platformId,
                    Integer dataSpace, String clientChannel) {
        this(number, customerId, sellerId, sellerName, goodsAmount, deliveryCost, couponId, couponDiscountAmount,
                walletPaymentId, walletPaymentAmount, pointsPaymentId, pointsPaymentAmount, processDeadline,
                shippingAddress, invoice, buyerRemark, platformId, dataSpace, clientChannel, null, null);
    }

    public void pay(BigDecimal amount) {
        // 更新订单项状态
        lineItems.forEach(lineItem -> {
            // todo 状态判断
            lineItem.setStatus(OrderStatusEnum.READY.getValue());
        });

        //todo 状态判断
//        this.paymentAmount = amount;
//        this.paymentTime = now;
        this.status = OrderStatusEnum.READY.getValue();
        this.processDeadline = null;
    }


    public void groupBuySuccess() {
        throw new RuntimeException("此订单类型不支持团购");
    }

    /**
     * 标星
     *
     * @param count
     */
    public void star(byte count) {
        this.starCount = count;
    }

    /**
     * 取消订单
     */
    public void cancel() {
        lineItems.forEach(orderItem -> orderItem.cancel());
        this.status = OrderStatusEnum.CLOSED.getValue();
    }

    /**
     * 删除订单
     */
    public void delete() {
        if (OrderStatusEnum.valueOf(status) != OrderStatusEnum.CLOSED) {
            throw new RuntimeException("此订单不可删除");
        }
        super.delete();
        lineItems.forEach(orderItem -> orderItem.delete());
    }

    /**
     * 收货
     */
    public void receive() {
        lineItems.forEach(orderItem -> orderItem.receive());
        this.status = OrderStatusEnum.COMPLETED.getValue();
    }

    public void addSellerRemark(String remark) {
        sellerRemark = remark;
    }

    public void editAddress(String receiverName, String receiverMobile, String receiverAddress) {
        this.receiverAddress = receiverAddress;
        this.receiverName = receiverName;
        this.receiverMobile = receiverMobile;
    }

    public void editTotalAmount(BigDecimal amount) {
        this.totalAmount = amount;
    }

    public void editDeliveryCost(BigDecimal cost) {
        this.deliveryCost = cost;
        refreshTotalAmount();
    }

    public void refreshTotalAmount() {
        this.totalAmount =
                goodsAmount.add(deliveryCost).subtract(couponDiscountAmount).subtract(walletPaymentAmount).subtract(pointsPaymentAmount);

    }

    /**
     * 发货
     *
     * @param companyCode
     * @param deliveryNo
     */
    public void ship(List<Long> orderItemIds, String companyCode, String deliveryNo) {
        LocalDateTime now = LocalDateTime.now();
        lineItems.stream().filter(orderItem -> orderItemIds.contains(orderItem.getId())).forEach(orderItem -> {
            orderItem.ship(companyCode, deliveryNo);
        });

        /* 如果订单项已全部发货，修改订单状态 */
        if (lineItems.stream().allMatch(orderItem -> orderItem.getStatus() == OrderStatusEnum.SHIPPED.getValue())) {
            this.status = OrderStatusEnum.SHIPPED.getValue();
            int receiveDays = 7; // todo param 确认收货时间
            this.processDeadline = now.plusDays(receiveDays);
            this.modifyTime = now;
        }
    }

    /**
     * 发货
     *
     * @param companyCode
     * @param deliveryNo
     */
    public void ship(String companyCode, String deliveryNo) {
        LocalDateTime now = LocalDateTime.now();
        lineItems.forEach(lineItem -> {
            lineItem.ship(companyCode, deliveryNo);
        });

        /* 如果订单项已全部发货，修改订单状态 */
        this.status = OrderStatusEnum.SHIPPED.getValue();
        int receiveDays = 7; // todo param 确认收货时间
        this.processDeadline = now.plusDays(receiveDays);
    }

    /**
     * 获取配送信息
     *
     * @return
     */
    public List<Delivery> getDeliveries() {
        return lineItems.stream().map(LineItem::getDelivery).collect(Collectors.toList());
    }

    /**
     * 获取购买件数
     *
     * @return
     */
    public Integer getPieceCount() {
        return lineItems.stream().mapToInt(LineItem::getQuantity).sum();
    }

    public LineItem getOrderItem(long orderItemId) {
        return lineItems.stream().filter(orderItem -> orderItem.getId() == orderItemId).findFirst().orElseThrow(() ->
                new RuntimeException("订单项不存在"));
    }

    public void addLineItem(LineItem lineItem) {
        this.lineItems.add(lineItem);
    }

    public void addLineItems(List<LineItem> lineItems) {
        this.lineItems.addAll(lineItems);
    }

    public void refund(long lineItemId) {
        LineItem lineItem =
                lineItems.stream().filter(li -> li.getId() == lineItemId).findAny().orElseThrow(() -> new RuntimeException("订单项不存在"));
        lineItem.refund();
        lineItems.forEach(li -> {
            if (lineItem.getStatus() != OrderStatusEnum.REFUNDED.getValue())
                return;
        });
        this.status = OrderStatusEnum.REFUNDED.getValue();
    }

    public void groupBuyTimeout() {
        this.status = OrderStatusEnum.GROUP_FAILURE.getValue();
        lineItems.forEach(orderItem -> {
            orderItem.groupBuyTimeout();
        });
    }
}
