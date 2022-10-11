package ru.geekbrains.march.market.models;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Cart {

    private List<Product> cart = new ArrayList<>();

    public void add(Product product) {
        cart.add(product);
    }

    public void remove(Product product) {
        cart.remove(product);
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(cart);
    }
}