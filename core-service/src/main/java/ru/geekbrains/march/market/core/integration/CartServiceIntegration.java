package ru.geekbrains.march.market.core.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.geekbrains.march.market.api.CartDto;
import ru.geekbrains.march.market.api.ProductDto;
import ru.geekbrains.march.market.core.exceptions.ResourceNotFoundException;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {
    private final WebClient cartServiceWebClient;

    public CartDto getCurrentCart(String username) {
        return cartServiceWebClient.get()
                .uri("/api/v1/cart/0")
                .header("username", username)
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus.value() == HttpStatus.NOT_FOUND.value(),
                        clientResponse -> Mono.error(new ResourceNotFoundException("Товар не найден в продуктовом МС"))
                )
                .bodyToMono(CartDto.class)
                .block();
    }

    public void cartClear(String username) {
        cartServiceWebClient.delete()
            .uri("/api/v1/cart/0/clear")
            .header("username", username)
            .retrieve()
            .toBodilessEntity()
            .block();
    }
}
