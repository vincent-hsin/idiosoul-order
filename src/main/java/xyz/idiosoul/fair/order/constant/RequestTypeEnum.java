package xyz.idiosoul.fair.order.constant;

import lombok.Getter;
import xyz.idiosoul.fair.order.infrastructure.CustomEnum;

/**
 * 售后类型
 */
public enum RequestTypeEnum implements CustomEnum {
    RETURN((byte) 1), REFUND((byte) 2);
    @Getter
    private byte value;

    RequestTypeEnum(byte value) {
        this.value = value;
    }

    public static RequestTypeEnum valueOf(byte value) {
        switch (value) {
            case 1:
                return RETURN;
            case 2:
                return REFUND;
            default:
                throw new RuntimeException("不存在的售后类型值");
        }
    }

    @Override
    public byte value() {
        return value;
    }
}
