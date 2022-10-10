package ru.geekbrains.march.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.march.market.dtos.ProductDto;
import ru.geekbrains.march.market.models.Product;
import ru.geekbrains.march.market.services.CartService;
import ru.geekbrains.march.market.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CartService cartService;

    @GetMapping
    public List<Product>[] getAllProducts() {
        return new List[]{productService.findAll(), cartService.getCartProducts()};
    }

    @GetMapping("/{id}")
    public void addToCart(@PathVariable("id") Long id) {
        cartService.addProductToCart(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewProducts(@RequestBody ProductDto createNewProductDto) {
        productService.createNewProduct(createNewProductDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @DeleteMapping("/cart/{id}")
    public void deleteProductOfCart(@PathVariable Long id) {
        cartService.removeProductFromCart(id);
    }
}
