package ru.geekbrains.march.market.cart.integrations;

import org.springframework.beans.factory.annotation.Value;
import ru.geekbrains.march.market.api.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {
    @Value("${path.core}")
    private String url;

    private final RestTemplate restTemplate;

    public ProductDto findById(Long id) {
        return restTemplate.getForObject(url + id, ProductDto.class);
    }
}
