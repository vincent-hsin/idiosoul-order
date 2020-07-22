package xyz.idiosoul.fair.order.domain.model.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.idiosoul.fair.order.infrastructure.service.NumberGenerator;
import xyz.idiosoul.fair.order.repository.AddressRepository;
import xyz.idiosoul.fair.order.repository.LineItemRepository;
import xyz.idiosoul.fair.order.repository.OrderRepository;
import xyz.idiosoul.fair.order.repository.PaymentRepository;
import xyz.idiosoul.fair.order.repository.RequestEventRepository;
import xyz.idiosoul.fair.order.repository.RequestRepository;
import xyz.idiosoul.fair.order.repository.ShoppingGroupRepository;

@Component
public class CustomerFactory {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private LineItemRepository lineItemRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private RequestEventRepository requestEventRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private ShoppingGroupRepository shoppingGroupRepository;
    @Autowired
    private NumberGenerator numberGenerator;

    public Customer getCustomer(int userId) {
        return new Customer(userId, lineItemRepository, addressRepository, requestRepository,
                requestEventRepository,
                paymentRepository,
                shoppingGroupRepository,
                numberGenerator, shoppingCartFactory);
    }
}
