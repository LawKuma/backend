package com.vrunk.vrunk_backend.dao;

import com.vrunk.vrunk_backend.entity.Categorie;
import java.util.List; 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long> {
    
    List<Categorie> findByNom(String nom);
}
