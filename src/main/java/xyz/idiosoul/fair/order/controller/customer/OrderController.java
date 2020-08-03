package xyz.idiosoul.fair.order.controller.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.idiosoul.fair.order.application.service.OrderService;
import xyz.idiosoul.fair.order.dto.OrderAddDTO;
import xyz.idiosoul.fair.order.util.RequestHeaderUtil;

import java.math.BigDecimal;
import java.util.List;

/**
 * 集市订单买家接口
 */
@Slf4j
//@Api(tags = "顾客-订单-集市（包括限时购、金币商城、满减、扶贫专区、家乡专区）")
@RestController
@RequestMapping("api/v1.0/customer/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

//    @ApiOperation("提交订单")
    @PostMapping
    public long placeOrder(@RequestBody OrderAddDTO orderAddDTO) {
        return orderService.placeOrder(RequestHeaderUtil.getCustomerId(),orderAddDTO.getAddressId(),
                orderAddDTO.getCartItems());
    }
}
