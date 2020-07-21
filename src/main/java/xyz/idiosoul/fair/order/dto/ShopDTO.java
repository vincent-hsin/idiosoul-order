package xyz.idiosoul.fair.order.dto;

import lombok.Getter;

@Getter
public class ShopDTO {
    public ShopDTO(long shopId, String shopName) {
        this.shopId = shopId;
        this.shopName = shopName;
    }

    private long shopId;
    private String shopName;
}
