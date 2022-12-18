package ru.geekbrains.march.market.core.converters;

import org.springframework.stereotype.Component;
import ru.geekbrains.march.market.api.OrderItemDto;
import ru.geekbrains.march.market.core.models.entities.OrderItem;

@Component
public class OrderItemConverter {
    public OrderItemDto entityToDto(OrderItem o) {
        return OrderItemDto.builder()
                .price(o.getPrice())
                .productId(o.getId())
                .quantity(o.getQuantity())
                .pricePerProduct(o.getPricePerProduct())
                .productTitle(o.getProduct().getTitle())
                .build();
    }
}
