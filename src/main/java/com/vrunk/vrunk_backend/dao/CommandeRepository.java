package com.vrunk.vrunk_backend.dao;

import com.vrunk.vrunk_backend.entity.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {
    
    List<Commande> findByClientEmail(String email);
    
    Commande findByNumeroSuiviCommande(String numeroSuiviCommande);

}
