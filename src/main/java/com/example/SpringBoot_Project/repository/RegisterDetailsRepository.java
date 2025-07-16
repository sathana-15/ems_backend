package com.example.SpringBoot_Project.repository;

import com.example.SpringBoot_Project.model.RegisterDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegisterDetailsRepository extends JpaRepository<RegisterDetails,Integer> {
    RegisterDetails findByEmail(String email);

    Optional<RegisterDetails> findByUserName(String username);

    @Query("SELECT r FROM RegisterDetails r JOIN r.roles role WHERE role.roleName = :roleName")
    Optional<RegisterDetails> findByRole(@Param("roleName") String roleName);
}
