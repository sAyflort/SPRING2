package ru.geekbrains.march.market.core.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.geekbrains.march.market.core.models.OrderDetail;
import ru.geekbrains.march.market.core.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestHeader String username, @RequestBody OrderDetail orderDetail) {
        orderService.createOrder(username, orderDetail);
    }
}
