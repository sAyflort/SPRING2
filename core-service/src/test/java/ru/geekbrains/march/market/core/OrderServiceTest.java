package ru.geekbrains.march.market.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import ru.geekbrains.march.market.api.CartDto;
import ru.geekbrains.march.market.api.CartItemDto;
import ru.geekbrains.march.market.core.integration.CartServiceIntegration;
import ru.geekbrains.march.market.core.models.OrderDetail;
import ru.geekbrains.march.market.core.models.entities.Order;
import ru.geekbrains.march.market.core.models.entities.Product;
import ru.geekbrains.march.market.core.repositories.OrderRepository;
import ru.geekbrains.march.market.core.services.OrderService;
import ru.geekbrains.march.market.core.services.ProductService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;

    @MockBean
    private CartServiceIntegration cartServiceIntegration;
    @MockBean
    private ProductService productService;
    @MockBean
    private OrderRepository orderRepository;

    private Product product;
    private CartDto cartDto;
    private OrderDetail orderDetail;

    @Test
    public void creatOrderTest() {
        CartItemDto cartItemDto = new CartItemDto(1L, "Product", 2,
                new BigDecimal(10),
                new BigDecimal(20));
        product = new Product();
        product.setTitle("Product");
        product.setId(1L);
        product.setPrice(BigDecimal.valueOf(10));
        cartDto = new CartDto(List.of(cartItemDto), cartItemDto.getPrice());
        orderDetail = new OrderDetail("улица Пушкина, дом Колотушкина", "88005553535");

        Mockito.doReturn(Optional.of(product)).when(productService).findById(1L);
        //Mockito.doReturn(cartDto).when(cartServiceIntegration).getCurrentCart();
        Assertions.assertEquals(BigDecimal.valueOf(20), orderService.createOrder("Ironman", orderDetail).getTotalPrice());
    }
}
