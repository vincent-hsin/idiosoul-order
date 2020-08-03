package xyz.idiosoul.fair.order.domain.model.order;

import org.springframework.stereotype.Component;
import xyz.idiosoul.fair.order.repository.OrderRepository;

@Component
public class OrderFactory {
    private OrderRepository orderRepository;

    public OrderFactory(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order ofCustomer(int customerId){
        return orderRepository.save(new Order(customerId));
    }
}
