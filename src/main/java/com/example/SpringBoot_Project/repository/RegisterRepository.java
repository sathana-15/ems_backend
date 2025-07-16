package com.example.SpringBoot_Project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.SpringBoot_Project.model.RegisterDetails;
import java.util.Optional;

public interface RegisterRepository extends JpaRepository<RegisterDetails,Integer> {
    Optional<RegisterDetails> findByUserName(String username);
}
