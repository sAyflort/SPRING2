package ru.geekbrains.march.market.core.services;

import ru.geekbrains.march.market.api.ProductDto;
import lombok.RequiredArgsConstructor;
import ru.geekbrains.march.market.core.models.entities.Product;
import ru.geekbrains.march.market.core.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> findAll(String titleFilter, BigDecimal minPriceFilter, BigDecimal maxPriceFilter) {
        return  productRepository.productsByFilter(minPriceFilter, maxPriceFilter, titleFilter);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public void createNewProduct(ProductDto pr) {
        Product product = new Product();
        product.setTitle(pr.getTitle());
        product.setPrice(pr.getPrice());
        productRepository.save(product);
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }
}
