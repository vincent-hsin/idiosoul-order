package xyz.idiosoul.fair.order.constant;

import lombok.Getter;
import xyz.idiosoul.fair.order.infrastructure.CustomEnum;

/**
 * 订单状态
 * <p>
 * 集市订单：SUBMITTED,PAID,SHIPPED,COMPLETED,CLOSED
 * 认养订单：GROUPING,
 */

public enum OrderStatusEnum implements CustomEnum {
    SUBMITTED((byte) 10), PROCESSING((byte) 20), WAITING((byte) 21), GROUPING((byte) 22), READY((byte) 30),
    SHIPPED((byte) 40), COMPLETED((byte) 50), /* 买家取消/卖家取消/支付超时 */ CLOSED((byte) 51), REFUNDED((byte) 52),
    GROUP_FAILURE((byte) 53);
    @Getter
    private byte value;

    OrderStatusEnum(byte value) {
        this.value = value;
    }

    public static OrderStatusEnum valueOf(Byte value) {
        switch (value) {
            case 10:
                return SUBMITTED;
            case 20:
                return PROCESSING;
            case 21:
                return WAITING;
            case 22:
                return GROUPING;
            case 30:
                return READY;
            case 40:
                return SHIPPED;
            case 50:
                return COMPLETED;
            case 51:
                return CLOSED;
            case 53:
                return GROUP_FAILURE;
            default:
                throw new RuntimeException("不存在的订单状态值");
        }
    }

    @Override
    public byte value() {
        return value;
    }
}
