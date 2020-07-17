package xyz.idiosoul.fair.order.domain.model.order;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import xyz.idiosoul.fair.order.constant.OrderStatusEnum;
import xyz.idiosoul.fair.order.domain.model.delivery.Delivery;
import xyz.idiosoul.fair.order.domain.model.request.Request;
import xyz.idiosoul.fair.order.infrastructure.EntityBase;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@ToString
@Entity
@Table(name = "t_order_line_item")
@DynamicInsert
@DynamicUpdate
public class LineItem extends EntityBase<Long> {
    private Long productId;
    private Long promotionProductId;
    private String productName;
    @Setter
    private Long skuId;
    private String specificationName;
    private String specificationValue;
    private String productImage;
    private BigDecimal unitPrice;
    @Setter
    private Integer quantity;
    @Setter
    private LocalDate deliveryDate;
    private String deliveryCompanyCode;
    private String deliveryNo;
    private Byte status;
    private String promotionTypes;
    private Long reviewId;

    private String receiverAddress, receiverName, receiverMobile;

    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "request_id", referencedColumnName = "id")
    private Request request;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    protected LineItem() {
    }

    public LineItem(Long skuId, Integer quantity, Long productId, Long promotionProductId, String productName,
                    String specificationName, String specificationValue, BigDecimal unitPrice, String productImage) {
        this.skuId = skuId;
        this.quantity = quantity;

        this.productId = productId;
        this.promotionProductId = promotionProductId;
        this.productName = productName;
        this.specificationName = specificationName;
        this.specificationValue = specificationValue;
        this.unitPrice = unitPrice;
        this.productImage = productImage;

        this.status = OrderStatusEnum.SUBMITTED.getValue();
    }

    public LineItem(Long productId, Long promotionProductId, String productName, BigDecimal unitPrice,
                    String productImage) {
        this(null, 1, productId, promotionProductId, productName, null, null, unitPrice,
                productImage);
    }

    public LineItem(Long skuId, Integer quantity, Long productId, String productName,
                    String specificationName, String specificationValue, BigDecimal unitPrice, String productImage) {
        this(skuId, quantity, productId, null, productName, specificationName, specificationValue, unitPrice,
                productImage);
    }

    public LineItem(Integer quantity, Long productId, String productName,
                    BigDecimal unitPrice, String productImage) {
        this(null, quantity, productId, null, productName, null, null, unitPrice,
                productImage);
    }

    public LineItem(String productName) {
        this.productName = productName;
        this.status = OrderStatusEnum.SUBMITTED.getValue();
    }

    public void setStatus(byte status) {
        this.status = status;
        this.modifyTime = LocalDateTime.now();
    }

    public void delete() {
        this.deleted = true;
    }

    public void editSpecification(long specificationId, String specificationName, String specificationValue) {
        this.skuId = specificationId;
        this.specificationName = specificationName;
        this.specificationValue = specificationValue;
        this.modifyTime = LocalDateTime.now();
    }

//    public void request(int requestId) {
//        this.requestId = requestId;
//        this.statusView = OrderItemStatusViewEnum.REQUESTING;
//    }

    public void review(long reviewId) {
        this.reviewId = reviewId;
    }

    public void cancel() {
        this.status = OrderStatusEnum.CLOSED.getValue();
        this.modifyTime = LocalDateTime.now();
    }

    public void ship(String deliveryCompanyCode, String deliveryNo) {
        this.deliveryCompanyCode = deliveryCompanyCode;
        this.deliveryNo = deliveryNo;
        this.status = OrderStatusEnum.SHIPPED.getValue();
    }

    public void editShipment(String deliveryCompanyCode, String deliveryNo) {
        this.deliveryCompanyCode = deliveryCompanyCode;
        this.deliveryNo = deliveryNo;
    }

    public void receive() {
        this.status = OrderStatusEnum.COMPLETED.getValue();
        this.modifyTime = LocalDateTime.now();
    }

//    public void pay()
//    {
//        this.status = OrderStatusEnum.PAID.getValue();
//        this.statusView = OrderItemStatusViewEnum.PAID;
//        this.modifyTime = LocalDateTime.now();
//    }

    public void complete() {
        this.status = OrderStatusEnum.COMPLETED.getValue();
        this.modifyTime = LocalDateTime.now();
    }

    public void close() {
        this.status = OrderStatusEnum.CLOSED.getValue();
        this.modifyTime = LocalDateTime.now();
    }

    public void editAddress(String receiverName, String receiverMobile, String receiverAddress) {
        this.receiverName = receiverName;
        this.receiverAddress = receiverAddress;
        this.receiverMobile = receiverMobile;
    }

    public void groupBuyTimeout() {
        this.status = OrderStatusEnum.GROUP_FAILURE.getValue();
    }

    public Delivery getDelivery() {
        return new Delivery(deliveryCompanyCode, deliveryNo);
    }

    public BigDecimal getAmount() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

    public void refund() {
        this.status = OrderStatusEnum.REFUNDED.getValue();
    }
}
