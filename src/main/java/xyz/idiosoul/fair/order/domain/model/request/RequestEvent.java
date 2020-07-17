package xyz.idiosoul.fair.order.domain.model.request;

import lombok.Data;
import xyz.idiosoul.fair.order.constant.RequestActionEnum;
import xyz.idiosoul.fair.order.infrastructure.EntityBase;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "t_order_request_event")
public class RequestEvent extends EntityBase<Integer> {
    private Integer channel;
    @Enumerated(EnumType.STRING)
    private RequestActionEnum action;
    private BigDecimal amount;
    private String reason;
    private String description;
    private String proofImages;

    protected RequestEvent() {
    }

    public RequestEvent(RequestActionEnum action, BigDecimal amount, String reason, String description,
                        String proofImages) {
        this.action = action;
        this.amount = amount;
        this.reason = reason;
        this.description = description;
        this.proofImages = proofImages;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
