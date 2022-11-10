package ru.geekbrains.march.market.cart.utils;

import ru.geekbrains.march.market.api.ProductDto;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Cart {
    private List<CartItem> items;
    private BigDecimal totalPrice;

    public void add(ProductDto p) {
        if(!changeQuantityProductById(p.getId(), 1)) {
            items.add(new CartItem(p.getId(), p.getTitle(), 1, p.getPrice(), p.getPrice()));
        }
        recalculate();
    }

    public void clear() {
        items.clear();
        totalPrice = BigDecimal.ZERO;
    }

    public boolean changeQuantityProductById(Long id, int change) {
        for (CartItem ci: items
        ) {
            if(ci.getProductId().equals(id)) {
                if (ci.getQuantity()+change >= 1) {
                    ci.changeQuantity(change);
                } else {
                    removeCartItem(id);
                }
                recalculate();
                return true;
            }
        }
        return false;
    }

    private void recalculate() {
        totalPrice = BigDecimal.ZERO;
        items.forEach(i -> totalPrice = totalPrice.add(i.getPrice()));
    }

    public void removeCartItem(Long id) {
        if(items.removeIf(ci -> ci.getProductId().equals(id))) {
            recalculate();
        }
    }
}
