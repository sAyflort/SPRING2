package ru.geekbrains.march.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.march.market.models.Cart;
import ru.geekbrains.march.market.models.Product;
import ru.geekbrains.march.market.repositories.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final Cart cart;
    private final ProductRepository productRepository;

    public void addProductToCart(Long id) {
        Product product = productRepository.findProductById(id).orElseThrow(() -> new NullPointerException("Product was not founded"));
        cart.add(product);
    }

    public void removeProductFromCart(Long id) {
        Product product = productRepository.findProductById(id).orElseThrow(() -> new NullPointerException("Product was not founded"));
        cart.remove(product);
    }

    public List<Product> getCartProducts() {
        return cart.getAllProducts();
    }

}