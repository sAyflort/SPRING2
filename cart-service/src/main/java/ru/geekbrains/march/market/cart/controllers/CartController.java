package ru.geekbrains.march.market.cart.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.march.market.api.CartDto;
import ru.geekbrains.march.market.api.StringResponse;
import ru.geekbrains.march.market.cart.converters.CartConverter;
import ru.geekbrains.march.market.cart.services.CartService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@Slf4j
public class CartController {
    private final CartService cartService;
    private final CartConverter cartConverter;

    @GetMapping("/generate_uuid")
    public StringResponse generateUuid() {
        return new StringResponse(UUID.randomUUID().toString());
    }

    @GetMapping("/{uuid}")
    public CartDto getCurrentCart(@RequestHeader(name = "username", required = false) String name, @PathVariable String uuid) {
        String targetUuid = getCartUuid(name, uuid);
        return cartConverter.entityToDto(cartService.getCurrentCart(targetUuid));
    }

    @GetMapping("/{uuid}/add/{productId}")
    public void addProductToCart(@RequestHeader(name = "username", required = false) String name, @PathVariable String uuid, @PathVariable Long productId) {
        String targetUuid = getCartUuid(name, uuid);
        cartService.addToCart(targetUuid, productId);
    }

    @DeleteMapping("/{uuid}/delete/{id}")
    public void deleteProductById(@RequestHeader(name = "username", required = false) String name, @PathVariable String uuid, @PathVariable("id") Long id){
        String targetUuid = getCartUuid(name, uuid);
        cartService.deleteCartItem(targetUuid, id);
    }

    @DeleteMapping("/{uuid}/clear")
    public void dropCartsProducts(@RequestHeader(name = "username", required = false) String name, @PathVariable String uuid){
        String targetUuid = getCartUuid(name, uuid);
        cartService.clearCart(targetUuid);
    }

    @GetMapping("/{uuid}/change/{id}/{change}")
    public void changeQuantity(
            @RequestHeader(name = "username", required = false) String name,
            @PathVariable String uuid,
            @PathVariable("id") Long id,
            @PathVariable("change") int change
    ) {
        String targetUuid = getCartUuid(name, uuid);
        cartService.changeQuantityProductById(targetUuid, id, change);
    }

    @GetMapping("/{uuid}/merge")
    public void mergeCarts(
            @RequestHeader(name = "username", required = false) String name,
            @PathVariable String uuid) {
        cartService.merge(uuid, name);
    }

    private String getCartUuid(String username, String uuid) {
        if(username != null) {
            return username;
        }
        return uuid;
    }
}
