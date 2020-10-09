package xyz.idiosoul.fair.order.application.service.impl;

import org.springframework.stereotype.Service;
import xyz.idiosoul.fair.order.application.service.AddressService;
import xyz.idiosoul.fair.order.domain.model.address.Address;
import xyz.idiosoul.fair.order.domain.model.user.Customer;
import xyz.idiosoul.fair.order.domain.model.user.CustomerFactory;
import xyz.idiosoul.fair.order.dto.AddressAddDTO;
import xyz.idiosoul.fair.order.dto.AddressEditDTO;
import xyz.idiosoul.fair.order.dto.RegionDTO;
import xyz.idiosoul.fair.order.vo.AddressVO;

import java.util.Objects;

@Service
public class AddressServiceImpl implements AddressService {
    private final CustomerFactory customerFactory;

    public AddressServiceImpl(CustomerFactory customerFactory) {
        this.customerFactory = customerFactory;
    }

    /**
     * 新增地址
     *
     * @param buyerId
     * @param addressAddDTO
     * @return
     */
    @Override
    public AddressVO add(int buyerId, AddressAddDTO addressAddDTO) {
        // 获取省市区的地区代码和名称
        String districtCode = addressAddDTO.getRegion();
        RegionDTO regionInfo = new RegionDTO("10086", "上海"); // Mock commonService.getRegionInfo(districtCode);

        Customer customer = customerFactory.getCustomer(buyerId);
        Address address = customer.createShippingAddress(regionInfo.getRegionCode(),
                regionInfo.getRegionName(),
                addressAddDTO.getAddress(), addressAddDTO.getContactName(), addressAddDTO.getContactMobile());
        return address.view();
    }

    @Override
    public AddressVO view(int buyerId, int addressId) {
        Customer customer = customerFactory.getCustomer(buyerId);
        return customer.getShippingAddress(addressId).view();
    }

    @Override
    public AddressVO edit(int buyerId, AddressEditDTO addressEditDTO) {
        Customer customer = customerFactory.getCustomer(buyerId);
        Address address = customer.getShippingAddress(addressEditDTO.getAddressId());

        String districtCode = addressEditDTO.getRegion();
        RegionDTO regionInfo = new RegionDTO("10086", "上海");// Mock commonService.getRegionInfo(districtCode);

        address.edit(regionInfo.getRegionCode(), regionInfo.getRegionName(), addressEditDTO.getAddress(),
                addressEditDTO.getContactName(), addressEditDTO.getContactMobile());
        return address.view();
    }

    @Override
    public void delete(int buyerId, int addressId) {
        Customer customer = customerFactory.getCustomer(buyerId);
        customer.getShippingAddress(addressId).delete();
    }

    @Override
    public AddressVO setDefault(int buyerId, int addressId) {
        Customer customer = customerFactory.getCustomer(buyerId);
        Address defaultAddress = customer.getDefaultShippingAddress();
        if (Objects.nonNull(defaultAddress)) {
            defaultAddress.cancelDefault();
        }
        Address newDefaultAddress = customer.getShippingAddress(addressId);
        newDefaultAddress.asDefault();
        return newDefaultAddress.view();
    }

    @Override
    public AddressVO getDefault(int buyerId) {
        AddressVO addressVO = null;
        Customer customer = customerFactory.getCustomer(buyerId);
        Address defaultAddress = customer.getDefaultShippingAddress();
        if (Objects.nonNull(defaultAddress)) {
            addressVO = defaultAddress.view();
        }
        return addressVO;
    }
}
