package xyz.idiosoul.fair.order.constant;

import lombok.Getter;

import java.util.Objects;

/**
 * 发票类型
 */
public enum InvoiceTypeEnum {
    PAPER((byte) 1), ELECTRONIC((byte) 2), VAT((byte) 3);
    @Getter
    private byte value;

    InvoiceTypeEnum(byte value) {
        this.value = value;
    }

    public static InvoiceTypeEnum valueOf(Byte value) {
        if (Objects.isNull(value))
            value = 0;
        switch (value) {
            case 1:
                return PAPER;
            case 2:
                return ELECTRONIC;
            case 3:
                return VAT;
            default:
                throw new RuntimeException("不存在的订单类型值");
        }
    }
}
