package xyz.idiosoul.fair.order.controller.customer;

import io.swagger.annotations.ApiImplicitParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.idiosoul.fair.order.application.service.AddressService;
import xyz.idiosoul.fair.order.dao.AddressMapper;
import xyz.idiosoul.fair.order.dto.AddressAddDTO;
import xyz.idiosoul.fair.order.dto.AddressEditDTO;
import xyz.idiosoul.fair.order.util.RequestHeaderUtil;
import xyz.idiosoul.fair.order.vo.AddressVO;

import javax.validation.Valid;
import java.util.List;

//@Api(tags = "顾客-收货地址")
@RestController
@RequestMapping("api/v1.0/consumer/address")
public class AddressController {
    private final AddressService addressService;
    private final AddressMapper addressMapper;

    public AddressController(AddressService addressService, AddressMapper addressMapper) {
        this.addressService = addressService;
        this.addressMapper = addressMapper;
    }

    //    @ApiOperation("新增地址")
    @PostMapping
    public AddressVO add(@Valid @RequestBody AddressAddDTO addressAddDTO) {
        int customerId = RequestHeaderUtil.getCustomerId();
        return addressService.add(customerId, addressAddDTO);
    }

    //    @ApiOperation("查看地址")
    @GetMapping
    @ApiImplicitParam(name = "addressId", value = "地址id")
    public AddressVO view(int addressId) {
        int customerId = RequestHeaderUtil.getCustomerId();
        return addressService.view(customerId, addressId);
    }

    //    @ApiOperation("获取默认地址")
    @GetMapping("default")
    public AddressVO getDefault() {
        int customerId = RequestHeaderUtil.getCustomerId();
        return addressService.getDefault(customerId);
    }

    //    @ApiOperation("地址列表")
    @GetMapping("list")
    public List<AddressVO> list() {
        int customerId = RequestHeaderUtil.getCustomerId();
        return addressMapper.listAddresses(customerId);
    }

    //    @ApiOperation("编辑地址")
    @PostMapping("edit")
    public AddressVO edit(@RequestBody AddressEditDTO address) {
        int customerId = RequestHeaderUtil.getCustomerId();
        return addressService.edit(customerId, address);
    }

    //    @ApiOperation("设为默认地址")
    @PostMapping("default")
    public AddressVO setDefault(int addressId) {
        int customerId = RequestHeaderUtil.getCustomerId();
        return addressService.setDefault(customerId, addressId);
    }

    //    @ApiOperation("删除地址")
    @PostMapping("delete")
    public void delete(int addressId) {
        int customerId = RequestHeaderUtil.getCustomerId();
        addressService.delete(customerId, addressId);
    }
}
