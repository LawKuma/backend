package com.vrunk.vrunk_backend.dao;

import com.vrunk.vrunk_backend.entity.Client;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByEmail(String email); // Utilisation d'Optional

    boolean existsByEmail(String email);

}
