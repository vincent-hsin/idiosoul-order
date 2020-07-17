package xyz.idiosoul.fair.order.constant;

import lombok.Getter;

import java.util.Objects;

/**
 * 发票title类型
 */
public enum InvoiceTitleTypeEnum {
    PERSONAL((byte) 1), COMPANY((byte) 2);
    @Getter
    private byte value;

    InvoiceTitleTypeEnum(byte value) {
        this.value = value;
    }

    public static InvoiceTitleTypeEnum valueOf(Byte value) {
        if (Objects.isNull(value))
            value = 0;
        switch (value) {
//            case 0:
//                return ALL;
            case 1:
                return PERSONAL;
            case 2:
                return COMPANY;
            default:
                throw new RuntimeException("不存在的发票title类型值");
        }
    }
}
