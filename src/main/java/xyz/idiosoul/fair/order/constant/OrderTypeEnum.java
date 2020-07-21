package xyz.idiosoul.fair.order.constant;

import lombok.Getter;
import xyz.idiosoul.fair.order.infrastructure.CustomEnum;

import java.util.Objects;

/**
 * 订单类型
 */
public enum OrderTypeEnum implements CustomEnum {
    /* 集市、认养、农耕、众筹、免费试吃、拼团、购物车 */
    JS((byte) 1), RY((byte) 2), NG((byte) 3), ZC((byte) 4), MS((byte) 5), PT((byte) 6), SC((byte) 10);
    @Getter
    private byte value;

    OrderTypeEnum(byte value) {
        this.value = value;
    }

    public static OrderTypeEnum valueOf(Byte value) {
        if (Objects.isNull(value))
            value = 0;
        switch (value) {
            case 1:
                return JS;
            case 2:
                return RY;
            case 3:
                return NG;
            case 4:
                return ZC;
            case 5:
                return MS;
            case 6:
                return PT;
            case 10:
                return SC;
            default:
                throw new RuntimeException("不存在的订单类型值");
        }
    }

    @Override
    public byte value() {
        return value;
    }
}
