package ru.geekbrains.march.market.core.services;

import lombok.RequiredArgsConstructor;
import ru.geekbrains.march.market.core.models.entities.Role;
import ru.geekbrains.march.market.core.repositories.RoleRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getUserRole() {
        return roleRepository.findByName("ROLE_USER").get();
    }
}