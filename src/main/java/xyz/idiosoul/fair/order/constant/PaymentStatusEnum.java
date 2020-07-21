package xyz.idiosoul.fair.order.constant;

import lombok.Getter;

/**
 * 支付状态
 */
public enum PaymentStatusEnum {
    REQUESTED((byte) 10), SUCCESS((byte) 20), FAILURE((byte) 21);
    @Getter
    private byte value;

    PaymentStatusEnum(byte value) {
        this.value = value;
    }

    public static PaymentStatusEnum valueOf(Byte value) {
        switch (value) {
            case 10:
                return REQUESTED;
            case 20:
                return SUCCESS;
            case 21:
                return FAILURE;
            default:
                throw new RuntimeException("不存在的支付状态值");
        }
    }
}
