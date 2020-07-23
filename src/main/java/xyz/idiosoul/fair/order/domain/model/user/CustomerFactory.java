package xyz.idiosoul.fair.order.domain.model.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.idiosoul.fair.order.domain.model.cart.ShoppingCartFactory;
import xyz.idiosoul.fair.order.repository.AddressRepository;
import xyz.idiosoul.fair.order.repository.PaymentRepository;

@Component
public class CustomerFactory {
    private final ShoppingCartFactory shoppingCartFactory;
    private final AddressRepository addressRepository;
    private final PaymentRepository paymentRepository;

    public CustomerFactory(ShoppingCartFactory shoppingCartFactory, AddressRepository addressRepository, PaymentRepository paymentRepository) {
        this.shoppingCartFactory = shoppingCartFactory;
        this.addressRepository = addressRepository;
        this.paymentRepository = paymentRepository;
    }


    public Customer getCustomer(int userId) {
        return new Customer(userId, addressRepository,
                paymentRepository, shoppingCartFactory);
    }
}
