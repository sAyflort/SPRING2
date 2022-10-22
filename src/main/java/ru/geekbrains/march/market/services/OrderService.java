package ru.geekbrains.march.market.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.geekbrains.march.market.models.Cart;
import ru.geekbrains.march.market.models.OrderDetail;
import ru.geekbrains.march.market.models.entities.Order;
import ru.geekbrains.march.market.models.entities.OrderItem;
import ru.geekbrains.march.market.models.entities.User;
import ru.geekbrains.march.market.repositories.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final CartService cartService;
    private final ProductService productService;
    private final OrderRepository orderRepository;

    public void createOrder(User user, OrderDetail orderDetail) {
        orderRepository.findByUser(user).ifPresent(order -> orderRepository.deleteById(order.getId()));
        Cart cart = cartService.getCurrentCart();
        List<OrderItem> orderItems = cart.getItems()
                .stream().map(item -> new OrderItem(null,
                        productService.findById(item.getProductId()).get(),
                        null, item.getQuantity(), item.getPricePerProduct(),
                        item.getPrice(), null, null)).collect(Collectors.toList());
        orderRepository.save(new Order(null, user, orderItems, orderDetail.getAddress(),
                orderDetail.getPhone(), cart.getTotalPrice(), null,null));

        /*Order order = orderRepository.findByUser(user).get();
        order.getOrderItems().forEach(orderItem -> log.info(orderItem.toString()));
        *//*System.out.println(order.getId());*/
    }
}
