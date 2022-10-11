package ru.geekbrains.march.market.dtos;

import ru.geekbrains.march.market.models.Product;

public class MapperProduct {
    public static ProductDto map(Product product) {
        return new ProductDto(product.getTitle(), product.getPrice());
    }
    public static Product map(ProductDto product) {
        return new Product(null, product.getTitle(), product.getPrice());
    }

}
