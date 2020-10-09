package xyz.idiosoul.fair.order.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class AddressVO {
    @ApiModelProperty("地址id")
    private int addressId;
    @ApiModelProperty("收货人姓名")
    private String contactName;
    @ApiModelProperty("收货人手机号")
    private String contactMobile;
    @ApiModelProperty("地区代码")
    private String region;
    @ApiModelProperty("地区中文名称")
    private String regionName;
    @ApiModelProperty("地址")
    private String address;
    @ApiModelProperty("完整地址")
    private String fullAddress;
    @ApiModelProperty("默认地址标志")
    private Boolean isDefault;

    public AddressVO(int addressId, String contactName, String contactMobile, String region, String regionName,
                     String address, String fullAddress, Boolean isDefault) {
        this.addressId = addressId;
        this.contactName = contactName;
        this.contactMobile = contactMobile;
        this.region = region;
        this.regionName = regionName;
        this.address = address;
        this.fullAddress = fullAddress;
        this.isDefault = isDefault;
    }
}
