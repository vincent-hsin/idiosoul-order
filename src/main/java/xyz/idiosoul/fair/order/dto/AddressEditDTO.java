package xyz.idiosoul.fair.order.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("收货地址-修改")
@Data
public class AddressEditDTO extends AddressAddDTO {
    @ApiModelProperty("地址id")
    private Integer addressId;
    private String regionName; //TODO del
}
