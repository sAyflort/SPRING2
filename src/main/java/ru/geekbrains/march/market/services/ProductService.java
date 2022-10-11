package ru.geekbrains.march.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.march.market.dtos.MapperProduct;
import ru.geekbrains.march.market.dtos.ProductDto;
import ru.geekbrains.march.market.models.entities.Product;
import ru.geekbrains.march.market.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public void createNewProduct(ProductDto pr) {
        productRepository.save(MapperProduct.map(pr));
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }
}
