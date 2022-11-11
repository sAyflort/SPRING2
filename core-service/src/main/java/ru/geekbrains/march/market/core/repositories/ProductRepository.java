package ru.geekbrains.march.market.core.repositories;

import org.springframework.data.jpa.repository.Query;
import ru.geekbrains.march.market.core.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findProductById(Long id);

    @Query(
            value = """
            select * from products p 
            where ((:minPriceFilter is null or p.price >= :minPriceFilter) 
            and (:maxPriceFilter is null or p.price <= :maxPriceFilter)
            and (:titleFilter is null or p.title like :titleFilter))
            """, nativeQuery = true
    )
    List<Product> productsByFilter(BigDecimal minPriceFilter, BigDecimal maxPriceFilter, String titleFilter);
}