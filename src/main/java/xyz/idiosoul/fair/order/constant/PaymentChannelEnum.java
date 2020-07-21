package xyz.idiosoul.fair.order.constant;

import lombok.Getter;
import xyz.idiosoul.fair.order.infrastructure.CustomEnum;

/**
 * 支付渠道
 */
public enum PaymentChannelEnum implements CustomEnum {
    ALIPAY((byte) 11), WXPAY((byte) 12), WALLET((byte) 21);
    @Getter
    private byte value;

    PaymentChannelEnum(byte value) {
        this.value = value;
    }

    public static PaymentChannelEnum valueOf(Byte value) {
        switch (value) {
            case 11:
                return ALIPAY;
            case 12:
                return WXPAY;
            case 21:
                return WALLET;
            default:
                throw new RuntimeException("不存在的支付方式值");
        }
    }

    @Override
    public byte value() {
        return value;
    }
}
