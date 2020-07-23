package xyz.idiosoul.fair.order.application.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import xyz.idiosoul.fair.order.application.service.ProductService;
import xyz.idiosoul.fair.order.application.service.ShopService;
import xyz.idiosoul.fair.order.application.service.ShoppingCartService;
import xyz.idiosoul.fair.order.domain.model.user.CustomerFactory;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class ShoppingCartServiceImplTest {
    static class ShoppingCartServiceImplTestContextConfiguration{
        @Bean
        public ShoppingCartService shoppingCartService(CustomerFactory customerFactory,ShopService shopService,ProductService productService){
            return new ShoppingCartServiceImpl(customerFactory,shopService,productService);
        }

        @Bean
        public ShopService shopService(){
            return new ShopServiceImpl();
        }

        @Bean
        public ProductService productService(){
            return new MockProductServiceImpl();
        }

//        @Bean
//        public CustomerFactory customerFactory(){
//            return new CustomerFactory();
//        }
    }

    @Test
    void add() {
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