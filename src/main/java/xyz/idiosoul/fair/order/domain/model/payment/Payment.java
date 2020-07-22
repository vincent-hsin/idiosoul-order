package xyz.idiosoul.fair.order.domain.model.payment;

import lombok.Getter;
import lombok.Setter;
import xyz.idiosoul.fair.order.constant.PaymentStatusEnum;
import xyz.idiosoul.fair.order.infrastructure.EntityBase;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 支付
 */
@Getter
@Entity
public class Payment extends EntityBase<Long> {
    @Setter
    private Byte status;
    private Byte channel;
    @Setter // todo reflect
    private LocalDateTime paymentTime;
    @Setter
    private String channelTradeStatus;
    @Setter
    private String channelTradeNo;
    private BigDecimal amount;
    @Setter
    private String notify;
    @Setter
    private String appId;

    protected Payment() {
        // for Hiberante
    }

    public Payment(Byte channel, BigDecimal amount, Integer userId) {
        this.channel = channel;
        this.amount = amount;
        this.status = PaymentStatusEnum.REQUESTED.getValue();
        this.createUserId = userId;
        this.createTime = LocalDateTime.now();
    }
}
