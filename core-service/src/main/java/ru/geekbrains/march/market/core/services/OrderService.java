package ru.geekbrains.march.market.core.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.march.market.api.CartDto;
import ru.geekbrains.march.market.core.integration.CartServiceIntegration;
import ru.geekbrains.march.market.core.models.OrderDetail;
import ru.geekbrains.march.market.core.models.entities.Order;
import ru.geekbrains.march.market.core.models.entities.OrderItem;
import ru.geekbrains.march.market.core.repositories.OrderRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final CartServiceIntegration cartServiceIntegration;
    private final ProductService productService;
    private final OrderRepository orderRepository;

    @Transactional
    public Order createOrder(String username, OrderDetail orderDetail) {
        CartDto cart = cartServiceIntegration.getCurrentCart(username);
        Order order = new Order();
        List<OrderItem> orderItems = cart.getItems()
                .stream().map(item -> new OrderItem(
                        productService.findById(item.getProductId()).get(),
                        order,
                        item.getQuantity(),
                        item.getPricePerProduct(),
                        item.getPrice())).collect(Collectors.toList());
        order.setOrderItems(orderItems);
        order.setAddress(orderDetail.getAddress());
        order.setPhone(orderDetail.getPhone());
        order.setTotalPrice(cart.getTotalPrice());
        order.setUsername(username);
        orderRepository.save(order);
        cartServiceIntegration.cartClear(username);
        return order;
    }

    public List<Order> findByUsername(String username) {
        return orderRepository.findByUsername(username);
    }

    public Optional<Order> getOrderById(Long id) {
        return Optional.of(orderRepository.getById(id));
    }
}
