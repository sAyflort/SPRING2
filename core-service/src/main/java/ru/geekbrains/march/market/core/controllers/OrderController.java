package ru.geekbrains.march.market.core.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.geekbrains.march.market.api.OrderDto;
import ru.geekbrains.march.market.core.converters.OrderConverter;
import ru.geekbrains.march.market.core.models.OrderDetail;
import ru.geekbrains.march.market.core.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;
    private final OrderConverter orderConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestHeader String username, @RequestBody OrderDetail orderDetail) {
        orderService.createOrder(username, orderDetail);
    }

    @GetMapping
    public List<OrderDto> getUserOrders(@RequestHeader String username) {
        return orderService.findByUsername(username).stream().map(orderConverter::entityToDto).collect(Collectors.toList());
    }
}
