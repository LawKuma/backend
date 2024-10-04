package com.vrunk.vrunk_backend.dao;

import com.vrunk.vrunk_backend.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProduitRepository extends JpaRepository<Produit, Long> {

    List<Produit> findByNom(String nom);

    List<Produit> findByActif(boolean actif);

    List<Produit> findByCategorieNom(String nomCategorie);

    List<Produit> findByCollectionNom(String nomCollection);
}
