package ru.geekbrains.march.market.core.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.march.market.api.ProductDto;
import lombok.RequiredArgsConstructor;
import ru.geekbrains.march.market.core.converters.ProductConverter;
import ru.geekbrains.march.market.core.exceptions.ResourceNotFoundException;
import ru.geekbrains.march.market.core.models.entities.Product;
import ru.geekbrains.march.market.core.repositories.specifications.ProductsSpecifications;
import ru.geekbrains.march.market.core.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;

    @GetMapping
    public Page<ProductDto> getAllProducts(
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "page_size", defaultValue = "10") Integer pageSize,
            @RequestParam(name = "title_part", required = false) String titlePart,
            @RequestParam(name = "min_price", required = false) Integer minPrice,
            @RequestParam(name = "max_price", required = false) Integer maxPrice
    ) {
        if (page < 1) {
            page = 1;
        }
        if(pageSize < 1) {
            pageSize = 10;
        }
        Specification<Product> spec = Specification.where(null);
        if (titlePart != null) {
            spec = spec.and(ProductsSpecifications.titleLike(titlePart));
        }
        if (minPrice != null) {
            spec = spec.and(ProductsSpecifications.priceGreaterOrEqualsThan(BigDecimal.valueOf(minPrice)));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductsSpecifications.priceLessThanOrEqualsThan(BigDecimal.valueOf(maxPrice)));
        }
        return productService.findAll(page - 1, pageSize, spec).map(productConverter::entityToDto);
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        Product pr = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Продукт с id: " + id + " не найден"));
        return productConverter.entityToDto(pr);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewProducts(@RequestBody ProductDto productDto) {
        productService.createNewProduct(productDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
