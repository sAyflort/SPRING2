package ru.geekbrains.march.market.core.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.march.market.api.ProductDto;
import ru.geekbrains.march.market.core.models.entities.Product;
import ru.geekbrains.march.market.core.repositories.ProductRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final HashMap<Long, Optional<Product>> productMap;

    public Page<Product> findAll(int page, int pageSize, Specification<Product> specification) {
        return productRepository.findAll(specification, PageRequest.of(page, pageSize));
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
        productMap.remove(id);
    }

    public void createNewProduct(ProductDto pr) {
        Product product = new Product();
        product.setTitle(pr.getTitle());
        product.setPrice(pr.getPrice());
        productRepository.save(product);
    }

    public Optional<Product> findById(Long id) {
        if (productMap.get(id)==null) {
            log.info("from BD");
            Optional<Product> product = productRepository.findById(id);
            productMap.put(product.get().getId(), product);
            return product;
        } else {
            log.info("from PM");
            return productMap.get(id);
        }
    }
}
