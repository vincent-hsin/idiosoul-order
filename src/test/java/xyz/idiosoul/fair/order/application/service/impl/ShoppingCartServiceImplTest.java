package xyz.idiosoul.fair.order.application.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

/**
 * 购物车 UnitTest
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
class ShoppingCartServiceImplTest {
    @TestConfiguration
    static class ShoppingCartServiceImplTestContextConfiguration {
        @Bean
        public ShoppingCartService shoppingCartService(CustomerFactory customerFactory) {
            return new ShoppingCartServiceImpl(customerFactory, shopService(), productService());
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
        public CustomerFactory customerFactory(ShoppingCartFactory shoppingCartFactory) {
            return new CustomerFactory(shoppingCartFactory, addressRepository, paymentRepository);
        }

        @Bean
        public ShoppingCartFactory shoppingCartFactory(ShoppingGroupFactory shoppingGroupFactory) {
            return new ShoppingCartFactory(shoppingGroupFactory);
        }

        @Bean
        public ShoppingGroupFactory shoppingGroupFactory(ShoppingGroupRepository shoppingGroupRepository) {
            return new ShoppingGroupFactory(shoppingGroupRepository);
        }

        @MockBean
        private AddressRepository addressRepository;
        @MockBean
        private PaymentRepository paymentRepository;
    }

    @MockBean
    private ShoppingGroupRepository shoppingGroupRepository;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @BeforeEach
    public void setUp() {
        ShoppingGroup shoppingGroup = new ShoppingGroup(1, 1);
        ShoppingItemAddDTO shoppingItemAddDTO = new ShoppingItemAddDTO(1, 1L, 9);
        shoppingGroup.add(shoppingItemAddDTO);
        Mockito.when(shoppingGroupRepository.save(any()))
                .thenReturn(shoppingGroup);
        List<ShoppingGroup> shoppingGroups = new ArrayList<>();
        for (int i = 2; i <= 10; i++) {
            ShoppingGroup sg = new ShoppingGroup(1, i);
            sg.add(shoppingItemAddDTO);
            shoppingGroups.add(shoppingGroup);
        }

        Mockito.when(shoppingGroupRepository.findAllByBuyerIdAndDeletedFalse(anyInt())).thenReturn(shoppingGroups);

    }

    @Order(1)
    @Test
    void add() {
        ShoppingItemAddDTO shoppingItemAddDTO = new ShoppingItemAddDTO(1, 1L, 1);
        shoppingCartService.add(1, shoppingItemAddDTO);
        shoppingCartService.add(2, shoppingItemAddDTO);
    }

    @Order(2)
    @Test
    void getCartItemCount() {
        int cartItemCount = shoppingCartService.getCartItemCount(1);
        assertThat(cartItemCount).isEqualTo(9);
    }

    @Order(3)
    @Test
    void editQuantity() {
        shoppingCartService.editQuantity(1, 1, 1, 8);
    }

    @Order(4)
    @Test
    void editSkuId() {
        shoppingCartService.editSkuId(1, 1, 1, 10);
    }

    @Order(5)
    @Test
    void deleteCartItems() {
        Map<Integer, Set<Integer>> shoppingMap = new HashMap<>();
        Set<Integer> items = new HashSet<>();
        items.add(1);
        shoppingMap.put(1, items);
        shoppingCartService.deleteCartItems(1, shoppingMap);
    }
}