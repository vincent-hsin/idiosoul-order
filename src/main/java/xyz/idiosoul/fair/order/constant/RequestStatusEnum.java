package xyz.idiosoul.fair.order.constant;

import lombok.Getter;
import xyz.idiosoul.fair.order.infrastructure.CustomEnum;

/**
 * 售后状态
 */
public enum RequestStatusEnum implements CustomEnum {
    SUBMITTED((byte) 10), AGREED((byte) 20), DISAGREED((byte) 21), PLAT_INTERVENTION((byte) 22), SHIPPED((byte) 30), REFUNDING((byte) 40), COMPLETED((byte) 50), CLOSED((byte) 51);
    @Getter
    private byte value;

    RequestStatusEnum(byte value) {
        this.value = value;
    }

    public static RequestStatusEnum valueOf(byte value) {
        switch (value) {
            case 10:
                return SUBMITTED;
            case 20:
                return AGREED;
            case 21:
                return DISAGREED;
            case 22:
                return PLAT_INTERVENTION;
            case 30:
                return SHIPPED;
            case 40:
                return REFUNDING;
            case 50:
                return COMPLETED;
            case 51:
                return CLOSED;

            default:
                throw new RuntimeException("不存在的售后状态值");
        }
    }

    @Override
    public byte value() {
        return value;
    }
}
