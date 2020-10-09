package xyz.idiosoul.fair.order.dto;

import lombok.Getter;

@Getter
public class RegionDTO {
    private String regionCode;
    private String regionName;

    public RegionDTO(String regionCode, String regionName) {
        this.regionCode = regionCode;
        this.regionName = regionName;
    }
}