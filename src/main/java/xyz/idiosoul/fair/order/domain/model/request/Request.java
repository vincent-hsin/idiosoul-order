package xyz.idiosoul.fair.order.domain.model.request;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import xyz.idiosoul.fair.order.constant.ArbitrationResultEnum;
import xyz.idiosoul.fair.order.constant.RequestActionEnum;
import xyz.idiosoul.fair.order.constant.RequestStatusEnum;
import xyz.idiosoul.fair.order.constant.RequestTypeEnum;
import xyz.idiosoul.fair.order.infrastructure.EntityBase;
import xyz.idiosoul.fair.order.domain.model.order.LineItem;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 售后请求（domain model）
 */
@Entity
@Data
@DynamicInsert
@DynamicUpdate
public class Request extends EntityBase<Integer> {
    private Long paymentId;
    private Byte type;
    private Integer returnQuantity;
    private BigDecimal refundAmount;
    private Byte reasonCode;
    private Byte status;

    private String deliveryCompanyCode;
    private String deliveryNo;
    private String buyerRemark;
    private String sellerRemark;
    private String buyerImages;
    private String sellerImages;
    private byte remainEditTimes = 3;
    private LocalDateTime processDeadline;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "request_id")
    private List<RequestEvent> requestEvents;

    @OneToOne(mappedBy = "request")
    private LineItem lineItem;

    protected Request() {
    }

    public Request(Byte type, BigDecimal refundAmount, Byte reasonCode, String reasonText, String buyerRemark,
                   String buyerImages, Long paymentId) {
        LocalDateTime now = LocalDateTime.now();
        this.type = type;
        this.refundAmount = refundAmount;
        this.reasonCode = reasonCode;
        this.buyerRemark = buyerRemark;
        this.buyerImages = buyerImages;
        this.paymentId = paymentId;

        this.remainEditTimes = 3; // 可修改3次
        this.createTime = now;
        this.status = RequestStatusEnum.SUBMITTED.getValue();
        this.processDeadline = now.plusDays(3); // TODO parameter

        /* 售后操作记录 */
        RequestActionEnum action;
        if (type == RequestTypeEnum.REFUND.getValue()) {
            action = RequestActionEnum.BUYER_REFUND;
        } else {
            action = RequestActionEnum.BUYER_RETURN;
        }

        RequestEvent requestEvent = new RequestEvent();
        requestEvent.setAction(action);
        requestEvent.setReason(reasonText);
        requestEvent.setDescription(buyerRemark);
        requestEvent.setProofImages(buyerImages);
        requestEvent.setCreateTime(LocalDateTime.now());
        requestEvent.setAmount(refundAmount);
        List<RequestEvent> requestEvents = new ArrayList<>();
        this.requestEvents = requestEvents;
        requestEvents.add(requestEvent);
    }

    /**
     * 发货
     */
    public void ship(String companyCode, String deliveryNo) {
        LocalDateTime now = LocalDateTime.now();

        this.deliveryCompanyCode = companyCode;
        this.deliveryNo = deliveryNo;
        this.status = RequestStatusEnum.SHIPPED.getValue();
        this.modifyTime = now;


        /* 售后操作记录 */
        RequestEvent requestEvent = new RequestEvent();
        requestEvent.setAction(RequestActionEnum.BUYER_SHIP);
        requestEvent.setCreateTime(now);

        List<RequestEvent> requestEvents = getRequestEvents();
        requestEvents.add(requestEvent);
    }

    private List<RequestEvent> getRequestEvents() {
        if (Objects.isNull(requestEvents)) {
            this.requestEvents = new ArrayList<>();
        }
        return requestEvents;
    }

    /**
     * 收货
     */
    public void receive() {
        LocalDateTime now = LocalDateTime.now();
        if (status != RequestStatusEnum.SHIPPED.getValue()) {
            throw new RuntimeException("此订单不是发货状态");
        }
        this.status = RequestStatusEnum.REFUNDING.getValue();
        this.modifyTime = now;


        /* 售后操作记录 */
        RequestEvent requestEvent = new RequestEvent();
        requestEvent.setAction(RequestActionEnum.SELLER_RECEIVE);
        requestEvent.setCreateTime(LocalDateTime.now());
        getRequestEvents().add(requestEvent);

    }

    /**
     * 取消售后申请
     */
    public void cancel() {
        LocalDateTime now = LocalDateTime.now();
        this.status = RequestStatusEnum.CLOSED.getValue();

        long nonClosedRequestCount =
                lineItem.getOrder().getLineItems().stream().filter(li -> li.getRequest().status != RequestStatusEnum.CLOSED.getValue()).count();

        /* 售后操作记录 */
        RequestEvent requestEvent = new RequestEvent();
        requestEvent.setAction(RequestActionEnum.BUYER_CANCEL);
        requestEvent.setCreateTime(now);
        getRequestEvents().add(requestEvent);
    }

    /**
     * 同意售后申请
     */
    public void agree() {
        // todo 订单状态校验
        switch (RequestTypeEnum.valueOf(type)) {
            case RETURN:
                this.status = RequestStatusEnum.AGREED.getValue();
                break;
            case REFUND:
                this.status = RequestStatusEnum.REFUNDING.getValue();
                break;
            default:
                throw new RuntimeException("售后类型不合法");

        }
        LocalDateTime now = LocalDateTime.now();
        this.processDeadline = now.plusDays(7); // todo param
        this.modifyTime = now;

        /* 如果是退款申请，调用退款接口 */
        // todo refund


        /* 售后操作记录 */
        RequestEvent requestEvent = new RequestEvent();
        requestEvent.setAction(RequestActionEnum.SELLER_AGREE);
        requestEvent.setCreateTime(LocalDateTime.now());
        getRequestEvents().add(requestEvent);
    }


    /**
     * 拒绝售后申请
     */

    public void disagree(String remark, String images) {
        // todo 订单状态校验
        LocalDateTime now = LocalDateTime.now();
        this.status = RequestStatusEnum.DISAGREED.getValue();
        this.processDeadline = now.plusDays(3);
        this.sellerRemark = remark;
        this.sellerImages = images;
        this.modifyTime = now;

        /* 售后操作记录 */
        RequestEvent requestEvent = new RequestEvent();
        requestEvent.setProofImages(images);
        requestEvent.setDescription(remark);
        requestEvent.setAction(RequestActionEnum.SELLER_DISAGREE);
        requestEvent.setCreateTime(LocalDateTime.now());
        getRequestEvents().add(requestEvent);
    }

    /**
     * 退款成功
     */
    public void refundSuccess() {
        LocalDateTime now = LocalDateTime.now();
        this.status = RequestStatusEnum.COMPLETED.getValue();
        this.modifyTime = now;

        /* 售后操作记录 */
        RequestEvent requestEvent = new RequestEvent();
        requestEvent.setAction(RequestActionEnum.REFUND_SUCCESS);
        requestEvent.setCreateTime(LocalDateTime.now());
        getRequestEvents().add(requestEvent);
    }

    /**
     * 申诉
     */

    public void appeal(String reason, String remark, String images) {
        LocalDateTime now = LocalDateTime.now();
//        // todo 订单状态校验
        this.status = RequestStatusEnum.PLAT_INTERVENTION.getValue();
        this.modifyTime = now;

        /* 售后操作记录 */
        RequestEvent requestEventPO = new RequestEvent();
        requestEventPO.setReason(reason);
        requestEventPO.setDescription(remark);
        requestEventPO.setProofImages(images);
        requestEventPO.setAction(RequestActionEnum.BUYER_APPEAL);
        requestEventPO.setCreateTime(LocalDateTime.now());
        getRequestEvents().add(requestEventPO);
    }

    public void withdraw() {
        LocalDateTime now = LocalDateTime.now();
//        // todo 订单状态校验
        this.status = RequestStatusEnum.DISAGREED.getValue();
        this.modifyTime = now;

        /* 售后操作记录 */
        RequestEvent requestEventPO = new RequestEvent();
//        requestEventPO.setReason(reason);
        requestEventPO.setDescription("买家取消投诉");
//        requestEventPO.setProofImages(images);
        requestEventPO.setAction(RequestActionEnum.BUYER_WITHDRAW);
        requestEventPO.setCreateTime(LocalDateTime.now());
        getRequestEvents().add(requestEventPO);
    }

    public void arbitration(ArbitrationResultEnum arbitrationResult) {
        RequestStatusEnum newRequestStatus;
        RequestActionEnum requestAction;
        if (arbitrationResult == ArbitrationResultEnum.SUPPORT_SELLER) {
            newRequestStatus = RequestStatusEnum.DISAGREED;
            requestAction = RequestActionEnum.PLAT_SUPPORT_SELLER;
        } else {
            newRequestStatus = RequestStatusEnum.AGREED;
            requestAction = RequestActionEnum.PLAT_SUPPORT_BUYER;
        }
        // todo 订单状态校验
        this.status = newRequestStatus.getValue();
        this.modifyTime = LocalDateTime.now();

        /* 售后操作记录 */
        RequestEvent requestEventPO = new RequestEvent();
        requestEventPO.setAction(requestAction);
        requestEventPO.setCreateTime(LocalDateTime.now());
        getRequestEvents().add(requestEventPO);
    }
}
