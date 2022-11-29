package ru.geekbrains.march.market.core.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.geekbrains.march.market.core.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
}