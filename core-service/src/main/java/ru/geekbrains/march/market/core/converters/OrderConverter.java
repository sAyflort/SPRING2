package ru.geekbrains.march.market.core.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.march.market.api.OrderDto;
import ru.geekbrains.march.market.core.models.entities.Order;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderConverter {
    private final OrderItemConverter orderItemConverter;

    public OrderDto entityToDto(Order o) {
        return OrderDto.builder()
                .id(o.getId())
                .items(o.getOrderItems().stream().map(orderItemConverter::entityToDto).collect(Collectors.toList()))
                .address(o.getAddress())
                .phone(o.getPhone())
                .totalPrice(o.getTotalPrice())
                .username(o.getUsername())
                .build();
    }
}
