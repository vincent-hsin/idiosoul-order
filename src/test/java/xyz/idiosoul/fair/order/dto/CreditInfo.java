package xyz.idiosoul.fair.order.dto;

import lombok.Data;

/**
 * 信用信息
 */
@Data
public class CreditInfo {
    private Integer status;
    OuterData data;

    @Data
    public static class OuterData {
        InnerData data;
    }

    @Data
    public static class InnerData {
        Entity entity;
    }

    @Data
    public static class Entity {
        String name;
    }
}