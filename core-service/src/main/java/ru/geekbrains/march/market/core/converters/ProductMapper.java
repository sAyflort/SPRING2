package ru.geekbrains.march.market.core.converters;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.geekbrains.march.market.api.ProductDto;
import ru.geekbrains.march.market.core.models.entities.Product;

@Mapper(
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        componentModel = "spring"
)
public interface ProductMapper {
    ProductDto entityToDto(Product p);
}
