package ru.geekbrains.march.market.core.converters;

import org.springframework.stereotype.Component;
import ru.geekbrains.march.market.api.OrderItemDto;
import ru.geekbrains.march.market.core.models.entities.OrderItem;

@Component
public class OrderItemConverter {
    public OrderItemDto entityToDto(OrderItem o) {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setPrice(o.getPrice());
        orderItemDto.setId(o.getId());
        orderItemDto.setQuantity(o.getQuantity());
        orderItemDto.setPricePerProduct(o.getPricePerProduct());
        orderItemDto.setProductTitle(o.getProduct().getTitle());
        return orderItemDto;
    }
}
