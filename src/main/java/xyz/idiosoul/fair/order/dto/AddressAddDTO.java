package xyz.idiosoul.fair.order.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import xyz.idiosoul.fair.order.annotation.ContactNumberConstraint;

@ApiModel("新增-收货地址")
@Data
public class AddressAddDTO {
    @ApiModelProperty("收货地区（区的zoneCode,如浦东新区：310115）")
    private String region;
    @ApiModelProperty("收货详细地址（街道、门牌）")
    private String address;
    @ApiModelProperty("收货人姓名")
    private String contactName;
    @ApiModelProperty("收货人手机号")
    @ContactNumberConstraint
    private String contactMobile;
}
