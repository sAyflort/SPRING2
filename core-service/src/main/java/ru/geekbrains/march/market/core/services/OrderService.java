package ru.geekbrains.march.market.core.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.geekbrains.march.market.core.models.OrderDetail;
import ru.geekbrains.march.market.core.models.entities.Order;
import ru.geekbrains.march.market.core.models.entities.User;
import ru.geekbrains.march.market.core.repositories.OrderRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
   // private final CartService cartService;
    private final ProductService productService;
    private final OrderRepository orderRepository;

    public void createOrder(User user, OrderDetail orderDetail) {
        /*orderRepository.findByUser(user).ifPresent(order -> orderRepository.deleteById(order.getId()));
        //Cart cart = cartService.getCurrentCart();
        Order order = new Order();
       *//* List<OrderItem> orderItems = cart.getItems()
                .stream().map(item -> new OrderItem(
                        productService.findById(item.getProductId()).get(),
                        order,
                        item.getQuantity(),
                        item.getPricePerProduct(),
                        item.getPrice())).collect(Collectors.toList());*//*
        order.setOrderItems(orderItems);
        order.setAddress(orderDetail.getAddress());
        order.setPhone(orderDetail.getPhone());
        order.setTotalPrice(cart.getTotalPrice());
        order.setUser(user);
        orderRepository.save(order);*/
    }
}
