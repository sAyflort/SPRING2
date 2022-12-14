package ru.geekbrains.march.market.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Schema(description = "Модель заказа")
public class OrderDto {

    @Schema(description = "ID заказа", required = true, example = "1")
    private Long id;
    @Schema(description = "Имя пользователя", required = true, example = "bob")
    private String username;
    @Schema(description = "Айтемы продуктов", required = false)
    private List<OrderItemDto> items;
    @Schema(description = "цена", required = false, example = "1000.00")
    private BigDecimal totalPrice;
    @Schema(description = "адрес", required = true, example = "ул. Пушкина, д. Колотушкина")
    private String address;
    @Schema(description = "телефон", required = true, example = "88005553535")
    private String phone;

}
