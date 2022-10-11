package ru.geekbrains.march.market.models;

import lombok.Data;
import ru.geekbrains.march.market.models.entities.Product;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Cart {

    private List<CartItem> items;
    private BigDecimal totalPrice;

    public void add(Product p) {
        CartItem cartItem = new CartItem(p.getId(), p.getTitle(), 1, p.getPrice(), p.getPrice());
        items.add(cartItem);
        recalculate();
    }

    public void clear() {
        items.clear();
        totalPrice = BigDecimal.ZERO;
    }

    private void recalculate() {
        totalPrice = BigDecimal.ZERO;
        items.forEach(i -> totalPrice = totalPrice.add(i.getPrice()));
    }
}