package xyz.idiosoul.fair.order.vo;

import lombok.Data;
import xyz.idiosoul.fair.order.constant.OrderStatusEnum;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
//@ApiModel("订单详情")
public class OrderDetail4CustomerVO {
//    @ApiModelProperty("合买标志")
//    private Boolean isGroupBuy;
//    @ApiModelProperty("合买人列表")
//    private List<GroupBuyMemberVO> groupMembers;
//    @ApiModelProperty("还差几人成团")
//    private Integer remainSeatCount;
//    @ApiModelProperty("订单id")
    private Long orderId;
//    @ApiModelProperty("订单状态")
    private OrderStatusEnum status;
//    @ApiModelProperty("处理截止时间")
    private Date processDeadline;
//    @ApiModelProperty("处理倒计时")
//    Long processCountdown;
//    @ApiModelProperty("收货人姓名")
    private String receiverName;
//    @ApiModelProperty("收货人地址")
    private String receiverAddress;
//    @ApiModelProperty("收货人电话")
    private String receiverMobile;
//    @ApiModelProperty("收货日期")
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private Date receiveDate;
//    @ApiModelProperty("商铺id")
//    private Integer shopId;
//    @ApiModelProperty("商铺名称")
//    private String shopName;
//    @ApiModelProperty("订单项")
    private List<OrderItem4CustomerVO> items;
//    @ApiModelProperty("优惠券折扣金额")
//    private BigDecimal couponDiscountAmount;
//    @ApiModelProperty("运费")
    private BigDecimal deliveryCost;
//    @ApiModelProperty("商品件数")
    private Integer goodsPieceCount;
//    @ApiModelProperty("小计金额")
    private BigDecimal goodsAmount;
//    @ApiModelProperty("订单总价")
    private BigDecimal totalAmount;
//    @ApiModelProperty("余额支付")
    private BigDecimal walletPaidAmount;
//    @ApiModelProperty("实际支付")
    private BigDecimal paidAmount;

//    @ApiModelProperty("买家留言")
//    private String buyerRemark;
//    @ApiModelProperty("发票信息")
//    private InvoiceVO invoice;
//    @ApiModelProperty("订单编号")
//    private String orderNo;
//    @ApiModelProperty("支付交易号")
//    private String paymentTradeNo;
//    @ApiModelProperty("创建时间")
//    private Date creatTime;
//    @ApiModelProperty("支付时间")
//    private Date paymentTime;
//    @ApiModelProperty("售后状态[可售后，退款中，退款成功]")
//    private RefundStatusEnum refundStatus; // todo del 售后状态应该对应订单项

//    @ApiModelProperty("活动（满减）优惠金额")
//    private BigDecimal promotionDiscountAmount;
//
//    @ApiModelProperty("金币抵扣金额")
//    private BigDecimal goldCoinDiscountAmount;
//    @ApiModelProperty("金币抵扣备注")
//    private BigDecimal goldCoinDiscountRemark;

//    public void setStatus(byte orderStatus) {
//        this.status = OrderStatusEnum.valueOf(orderStatus);
//    }

    public int getGoodsPieceCount() {
        return items.stream().mapToInt(item -> item.getQuantity()).sum();
    }

//    public void setRefundStatus(byte refundStatus) {
//        this.refundStatus = RefundStatusEnum.valueOf(refundStatus);
//    }


}
