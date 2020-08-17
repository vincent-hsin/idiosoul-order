package xyz.idiosoul.fair.order.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import xyz.idiosoul.fair.order.vo.OrderDetail4CustomerVO;

@Repository
@Mapper
public interface OrderMapper {
    /**
     * 查询订单详情（客户端）
     *
     * @param customerId
     * @param orderId
     * @return
     */
    OrderDetail4CustomerVO getOrderDetail4Customer(Integer customerId, Long orderId);
}
