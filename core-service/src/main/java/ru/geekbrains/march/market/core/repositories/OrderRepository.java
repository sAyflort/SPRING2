package ru.geekbrains.march.market.core.repositories;

import ru.geekbrains.march.market.core.models.entities.Order;
import ru.geekbrains.march.market.core.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByUser(User user);
}