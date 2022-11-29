package ru.geekbrains.march.market.core.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
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

    public Page<Product> findAll(int page, int pageSize, Specification<Product> specification) {
        return productRepository.findAll(specification, PageRequest.of(page, pageSize));
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
