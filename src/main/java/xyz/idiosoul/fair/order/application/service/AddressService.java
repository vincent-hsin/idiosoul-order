package xyz.idiosoul.fair.order.application.service;

import org.springframework.transaction.annotation.Transactional;
import xyz.idiosoul.fair.order.dto.AddressAddDTO;
import xyz.idiosoul.fair.order.dto.AddressEditDTO;
import xyz.idiosoul.fair.order.vo.AddressVO;

public interface AddressService {

    @Transactional(rollbackFor = Exception.class)
    AddressVO add(int buyerId, AddressAddDTO addressAddDTO);

    AddressVO view(int buyerId, int addressId);

    @Transactional(rollbackFor = Exception.class)
    AddressVO edit(int buyerId, AddressEditDTO address);

    @Transactional(rollbackFor = Exception.class)
    void delete(int buyerId, int addressId);

    @Transactional(rollbackFor = Exception.class)
    AddressVO setDefault(int buyerId, int addressId);

    AddressVO getDefault(int buyerId);

}
