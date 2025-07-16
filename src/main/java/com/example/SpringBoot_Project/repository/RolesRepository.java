package com.example.SpringBoot_Project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.SpringBoot_Project.model.Roles;
import java.util.Optional;

public interface RolesRepository extends JpaRepository<Roles,Integer> {
    Optional<Roles> findByRoleName(String roleName);
}
