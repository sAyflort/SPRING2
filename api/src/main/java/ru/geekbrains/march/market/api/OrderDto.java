package ru.geekbrains.march.market.api;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderDto {
    private Long id;
    private String username;
    private List<OrderItemDto> items;
    private BigDecimal totalPrice;
    private String address;
    private String phone;

}
