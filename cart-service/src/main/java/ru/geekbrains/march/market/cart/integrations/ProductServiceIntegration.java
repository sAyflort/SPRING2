package ru.geekbrains.march.market.cart.integrations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.geekbrains.march.market.api.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.geekbrains.march.market.cart.exceptions.ResourceNotFoundException;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {
    private final WebClient productServiceWebClient;

    public ProductDto findById(Long id) {
          return productServiceWebClient.get()
                  .uri("/api/v1/products/"+id)
                  .retrieve()
                  .onStatus(
                          httpStatus -> httpStatus.value() == HttpStatus.NOT_FOUND.value(),
                          clientResponse -> Mono.error(new ResourceNotFoundException("Товар не найден в продуктовом МС"))
                  )
                  .bodyToMono(ProductDto.class)
                  .block();
    }
}
