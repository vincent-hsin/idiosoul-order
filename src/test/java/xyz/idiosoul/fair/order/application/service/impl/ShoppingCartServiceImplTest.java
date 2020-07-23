package xyz.idiosoul.fair.order.application.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import xyz.idiosoul.fair.order.application.service.ProductService;
import xyz.idiosoul.fair.order.application.service.ShopService;
import xyz.idiosoul.fair.order.application.service.ShoppingCartService;
import xyz.idiosoul.fair.order.domain.model.cart.ShoppingCartFactory;
import xyz.idiosoul.fair.order.domain.model.cart.ShoppingGroup;
import xyz.idiosoul.fair.order.domain.model.cart.ShoppingGroupFactory;
import xyz.idiosoul.fair.order.domain.model.user.CustomerFactory;
import xyz.idiosoul.fair.order.dto.ShoppingItemAddDTO;
import xyz.idiosoul.fair.order.repository.AddressRepository;
import xyz.idiosoul.fair.order.repository.PaymentRepository;
import xyz.idiosoul.fair.order.repository.ShoppingGroupRepository;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(SpringExtension.class)
class ShoppingCartServiceImplTest {
    @TestConfiguration
    static class ShoppingCartServiceImplTestContextConfiguration {
        @Bean
        public ShoppingCartService shoppingCartService() {
            return new ShoppingCartServiceImpl(customerFactory(), shopService(), productService());
        }

        @Bean
        public ShopService shopService() {
            return new ShopServiceImpl();
        }

        @Bean
        public ProductService productService() {
            return new MockProductServiceImpl();
        }

        @Bean
        public CustomerFactory customerFactory() {
            ShoppingGroup shoppingGroup = new ShoppingGroup(1, 1);
            Mockito.when(shoppingGroupRepository.save(any()))
                    .thenReturn(shoppingGroup);
            List<ShoppingGroup> shoppingGroups = new ArrayList<>();
            Mockito.when(shoppingGroupRepository.findAllByBuyerIdAndDeletedFalse(anyInt())).thenReturn(shoppingGroups);

            return new CustomerFactory(shoppingCartFactory(), addressRepository, paymentRepository);
        }

        @Bean
        public ShoppingCartFactory shoppingCartFactory() {
            return new ShoppingCartFactory(shoppingGroupFactory(), shoppingGroupRepository);
        }

        @Bean
        public ShoppingGroupFactory shoppingGroupFactory() {
            return new ShoppingGroupFactory(shoppingGroupRepository);
        }

        @MockBean
        private AddressRepository addressRepository;
        @MockBean
        private PaymentRepository paymentRepository;
        @MockBean
        static ShoppingGroupRepository shoppingGroupRepository;
    }


    @Autowired
    private ShoppingCartService shoppingCartService;

    @BeforeEach
    public void setUp() {
    }

    @Test
    void add() {
        ShoppingItemAddDTO shoppingItemAddDTO = new ShoppingItemAddDTO(1, 1L, 1);
        shoppingCartService.add(1, shoppingItemAddDTO);
    }

    @Test
    void getCartItemCount() {
    }

    @Test
    void editQuantity() {
    }

    @Test
    void editSpecificationId() {
    }

    @Test
    void deleteCartItems() {
    }
}