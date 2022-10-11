package ru.geekbrains.march.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.march.market.exceptions.ResourceNotFoundException;
import ru.geekbrains.march.market.models.Cart;
import ru.geekbrains.march.market.models.entities.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ProductService productService;
    private Cart cart;

    @PostConstruct
    public void init() {
        cart = new Cart();
        cart.setItems(new ArrayList<>());
    }

    public Cart getCurrentCart() {
        return cart;
    }

    public void clearCart() {
        cart.clear();
    }

    public void addToCart(Long productId) {
        Product p = productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Продукт с id: " + productId + " не найден"));
        cart.add(p);
    }

    public void changeQuantityProductById(Long id, int change) {
        cart.changeQuantityProductById(id, change);
    }

    public void deleteCartItem(Long id) {
        cart.removeCartItem(id);
    }
}