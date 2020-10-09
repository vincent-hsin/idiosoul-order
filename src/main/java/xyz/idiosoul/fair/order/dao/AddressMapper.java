package xyz.idiosoul.fair.order.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import xyz.idiosoul.fair.order.vo.AddressVO;

import java.util.List;

@Repository
@Mapper
public interface AddressMapper {
    List<AddressVO> listAddresses(int userId);
}
