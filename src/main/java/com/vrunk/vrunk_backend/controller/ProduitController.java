package com.vrunk.vrunk_backend.controller;

import com.vrunk.vrunk_backend.entity.Produit;
import com.vrunk.vrunk_backend.service.ProduitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produits")
public class ProduitController {

    private final ProduitService produitService;

    public ProduitController(ProduitService produitService) {
        this.produitService = produitService;
    }

    // Récupérer tous les produits
    @GetMapping
    public ResponseEntity<List<Produit>> obtenirTousLesProduits() {
        List<Produit> produits = produitService.obtenirTousLesProduits();
        return ResponseEntity.ok(produits);
    }

    // Récupérer un produit par ID
    @GetMapping("/{id}")
    public ResponseEntity<Produit> obtenirProduitParId(@PathVariable("id") Long id) {
        Produit produit = produitService.obtenirProduitParId(id);
        return ResponseEntity.ok(produit);
    }

    // Créer un nouveau produit
    @PostMapping("/creer")
    public ResponseEntity<Produit> creerProduit(@RequestBody Produit produit) {
        Produit nouveauProduit = produitService.creerProduit(produit);
        return ResponseEntity.ok(nouveauProduit);
    }

    // Mettre à jour un produit
    @PutMapping("/mettre-a-jour/{id}")
    public ResponseEntity<Produit> mettreAJourProduit(@PathVariable("id") Long id, @RequestBody Produit produit) {
        Produit produitMisAJour = produitService.mettreAJourProduit(id, produit);
        return ResponseEntity.ok(produitMisAJour);
    }

    // Supprimer un produit
    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<String> supprimerProduit(@PathVariable("id") Long id) {
        produitService.supprimerProduit(id);
        return ResponseEntity.ok("Produit supprimé avec succès.");
    }
}
