package ru.geekbrains.march.market.core.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.geekbrains.march.market.api.CartDto;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {
    @Value("${path.cart}")
    private String url;

    private final RestTemplate restTemplate;

    public CartDto getCurrentCart() {
        return restTemplate.getForObject(url, CartDto.class);
    }

    public void cartClear() {
        restTemplate.delete(url + "/clear");
    }
}
