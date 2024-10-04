package com.vrunk.vrunk_backend.service;

import com.vrunk.vrunk_backend.entity.Produit;
import com.vrunk.vrunk_backend.dao.ProduitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduitService {

    private final ProduitRepository produitRepository;

    public ProduitService(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    public List<Produit> obtenirTousLesProduits() {
        return produitRepository.findAll();
    }

    public Produit obtenirProduitParId(Long id) {
        return produitRepository.findById(id).orElseThrow(() -> new RuntimeException("Produit non trouvé."));
    }

    public Produit creerProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    public Produit mettreAJourProduit(Long id, Produit produitMisAJour) {
        Produit produit = obtenirProduitParId(id);
        produit.setNom(produitMisAJour.getNom());
        produit.setDescription(produitMisAJour.getDescription());
        produit.setPrix(produitMisAJour.getPrix());
        produit.setCategorie(produitMisAJour.getCategorie());
        produit.setCollection(produitMisAJour.getCollection());
        produit.setImageUrl(produitMisAJour.getImageUrl());
        produit.setActif(produitMisAJour.isActif());
        produit.setStock(produitMisAJour.getStock());
        produit.setTags(produitMisAJour.getTags());
        return produitRepository.save(produit);
    }

    public void supprimerProduit(Long id) {
        produitRepository.deleteById(id);
    }
}
