package ru.geekbrains.march.market.cart.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import ru.geekbrains.march.market.api.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.march.market.cart.integrations.ProductServiceIntegration;
import ru.geekbrains.march.market.cart.utils.Cart;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;

    @Value("${cart-service.cart-prefix}")
    private String cartPrefix;

    private Map<String, Cart> carts;

    @PostConstruct
    public void init() {
        carts = new HashMap<>();
    }

    public Cart getCurrentCart(String uuid) {
        String targetUuid = cartPrefix + uuid;
        if (!carts.containsKey(targetUuid)) {
            Cart cart = new Cart();
            cart.setItems(new ArrayList<>());
            carts.put(targetUuid, cart);
        }
        return  carts.get(targetUuid);
    }

    public void addToCart(String uuid, Long productId) {
        ProductDto product = productServiceIntegration.findById(productId);
        getCurrentCart(uuid).add(product);
    }

    public void clearCart(String uuid) {
        getCurrentCart(uuid).clear();
    }

    public void changeQuantityProductById(String uuid, Long id, int change) {
        getCurrentCart(uuid).changeQuantityProductById(id, change);
    }

    public void deleteCartItem(String uuid, Long id) {
        getCurrentCart(uuid).removeCartItem(id);
    }

    public void merge(String uuid, String name) {
        Cart cartByUuid = getCurrentCart(uuid);
        Cart cartByName = getCurrentCart(name);
        cartByName.addAllCartItem(cartByUuid.getItems());
        cartByUuid.clear();
    }
    /*
    * private final RedisTemplate<String, Object> redisTemplate;

    @Value("${cart-service.cart-prefix}")
    private String cartPrefix;

    public Cart getCurrentCart(String uuid) {
        String targetUuid = cartPrefix + uuid;
        if(!redisTemplate.hasKey(targetUuid)) {
            Cart cart = new Cart();
            cart.setItems(new ArrayList<>());
            redisTemplate.opsForValue().set(targetUuid, cart);
        }
        return (Cart) redisTemplate.opsForValue().get(targetUuid);
    }

    public void addToCart(String uuid, Long productId) {
        ProductDto p = productServiceIntegration.findById(productId);
        execute(uuid, cart -> cart.add(p));
    }

    public void clearCart(String uuid) {
        execute(uuid, Cart::clear);
    }

    public void changeQuantityProductById(String uuid, Long id, int change) {
        execute(uuid, cart -> cart.changeQuantityProductById(id, change));
    }

    public void deleteCartItem(String uuid, Long id) {
        execute(uuid, cart -> cart.removeCartItem(id));
    }

    private void execute(String uuid ,Consumer<Cart> cartConsumer) {
        Cart cart = getCurrentCart(uuid);
        cartConsumer.accept(cart);
        redisTemplate.opsForValue().set(cartPrefix + uuid ,cart);
    }*/
}
