package xyz.idiosoul.fair.order.controller.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.idiosoul.fair.order.application.service.OrderService;
import xyz.idiosoul.fair.order.dao.OrderMapper;
import xyz.idiosoul.fair.order.dto.OrderAddDTO;
import xyz.idiosoul.fair.order.util.RequestHeaderUtil;
import xyz.idiosoul.fair.order.vo.OrderDetail4CustomerVO;

/**
 * 集市订单买家接口
 */
@Slf4j
//@Api(tags = "顾客-订单-集市（包括限时购、金币商城、满减、扶贫专区、家乡专区）")
@RestController
@RequestMapping("api/v1.0/customer/order")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

//    @ApiOperation("提交订单")
    @PostMapping
    public long placeOrder(@RequestBody OrderAddDTO orderAddDTO) {
        int customerId = RequestHeaderUtil.getCustomerId();
        return orderService.placeOrder(customerId,orderAddDTO.getAddressId(),
                orderAddDTO.getCartItems());
    }

//    @ApiOperation("订单详情")
    @GetMapping
//    @ApiImplicitParam(name = "orderId", value = "订单id")
    public OrderDetail4CustomerVO detail(long orderId) {
        int customerId = RequestHeaderUtil.getCustomerId();
        return orderMapper.getOrderDetail4Customer(customerId, orderId);
    }
}
