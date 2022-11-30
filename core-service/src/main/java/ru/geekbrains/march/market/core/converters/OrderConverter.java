package ru.geekbrains.march.market.core.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.march.market.api.CartDto;
import ru.geekbrains.march.market.api.OrderDto;
import ru.geekbrains.march.market.core.models.entities.Order;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderConverter {
    private final OrderItemConverter orderItemConverter;

    public OrderDto entityToDto(Order o) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(o.getId());
        orderDto.setItems(o.getOrderItems().stream().map(orderItemConverter::entityToDto).collect(Collectors.toList()));
        orderDto.setAddress(o.getAddress());
        orderDto.setPhone(o.getPhone());
        orderDto.setTotalPrice(o.getTotalPrice());
        orderDto.setUsername(o.getUsername());
        return orderDto;
    }
}
