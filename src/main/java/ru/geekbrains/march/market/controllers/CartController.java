package ru.geekbrains.march.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.march.market.models.Cart;
import ru.geekbrains.march.market.models.CartItem;
import ru.geekbrains.march.market.services.CartService;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping
    public Cart getCurrentCart() {
        return cartService.getCurrentCart();
    }

    @GetMapping("/add/{productId}")
    public void addProductToCart(@PathVariable Long productId) {
        cartService.addToCart(productId);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProductById(@PathVariable("id") Long id){
        cartService.deleteCartItem(id);
    }

    @DeleteMapping("/delete")
    public void dropCartsProducts(){
        cartService.clearCart();
    }

    @GetMapping("/changeQuantity/{id}/{change}")
    public void changeQuantity(@PathVariable("id") Long id,
                               @PathVariable("change") int change) {
        cartService.changeQuantityProductById(id, change);
    }
}
