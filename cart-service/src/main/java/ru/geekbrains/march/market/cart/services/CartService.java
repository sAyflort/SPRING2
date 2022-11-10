package ru.geekbrains.march.market.cart.services;

import lombok.extern.slf4j.Slf4j;
import ru.geekbrains.march.market.api.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.march.market.cart.integrations.ProductServiceIntegration;
import ru.geekbrains.march.market.cart.utils.Cart;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;
    private Cart cart;

    @PostConstruct
    public void init() {
        cart = new Cart();
        cart.setItems(new ArrayList<>());
    }

    public Cart getCurrentCart() {
        return cart;
    }

    public void addToCart(Long productId) {
        ProductDto p = productServiceIntegration.findById(productId);
        cart.add(p);
    }

    public void clearCart() {
        cart.clear();
    }

    public void changeQuantityProductById(Long id, int change) {
        cart.changeQuantityProductById(id, change);
    }

    public void deleteCartItem(Long id) {
        cart.removeCartItem(id);
    }
}
