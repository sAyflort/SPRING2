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
        //test
        orderRepository.findByUser(user).ifPresent(order -> orderRepository.deleteById(order.getId()));
        Cart cart = cartService.getCurrentCart();
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
        order.setUser(user);
        orderRepository.save(order);
    }
}
