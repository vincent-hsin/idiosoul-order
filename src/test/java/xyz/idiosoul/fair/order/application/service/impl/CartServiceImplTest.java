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
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import xyz.idiosoul.fair.order.application.service.ProductService;
import xyz.idiosoul.fair.order.application.service.ShopService;
import xyz.idiosoul.fair.order.application.service.CartService;
import xyz.idiosoul.fair.order.domain.model.cart.Cart;
import xyz.idiosoul.fair.order.domain.model.cart.CartFactory;
import xyz.idiosoul.fair.order.dto.CartItemAddDTO;
import xyz.idiosoul.fair.order.repository.AddressRepository;
import xyz.idiosoul.fair.order.repository.CartRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

/**
 * 购物车 UnitTest
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
class CartServiceImplTest {
    @TestConfiguration
    static class ShoppingCartServiceImplTestContextConfiguration {
        @Bean
        public CartService cartService(CartFactory cartFactory, StringRedisTemplate stringRedisTemplate) {
            return new CartServiceImpl(cartFactory, stringRedisTemplate);
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
        public CartFactory shoppingGroupFactory(CartRepository cartRepository) {
            return new CartFactory(cartRepository);
        }

        @MockBean
        private AddressRepository addressRepository;
    }

    @MockBean
    private CartRepository cartRepository;

    @Autowired
    private CartService cartService;

    @BeforeEach
    public void setUp() {
        Cart cart = new Cart(1);
        CartItemAddDTO cartItemAddDTO = new CartItemAddDTO(1L, 1);
        cart.add(cartItemAddDTO);
        Mockito.when(cartRepository.save(any()))
                .thenReturn(cart);
        for (long i = 2; i <= 9; i++) {
            cartItemAddDTO = new CartItemAddDTO(i, 1);
            cart.add(cartItemAddDTO);
        }

        Mockito.when(cartRepository.findByCustomerIdAndDeletedFalse(anyInt())).thenReturn(Optional.of(cart));

    }

    @Order(1)
    @Test
    void add() {
        CartItemAddDTO cartItemAddDTO = new CartItemAddDTO(1L, 1);
        cartService.add(1, cartItemAddDTO);
        cartService.add(2, cartItemAddDTO);
    }

    @Order(2)
    @Test
    void getCartItemCount() {
        int cartItemCount = cartService.getCartItemsCount(1);
        assertThat(cartItemCount).isEqualTo(9);
    }

    @Order(3)
    @Test
    void editQuantity() {
        cartService.editQuantity(1, 1, 1);
    }

    @Order(4)
    @Test
    void editSkuId() {
        cartService.editSkuId(1, 1, 1);
    }

    @Order(5)
    @Test
    void deleteCartItems() {
//        Map<Integer, Set<Integer>> shoppingMap = new HashMap<>();
        Set<Long> skuIds = new HashSet<>();
        skuIds.add(1L);
        cartService.deleteCartItems(1, skuIds);
    }
}