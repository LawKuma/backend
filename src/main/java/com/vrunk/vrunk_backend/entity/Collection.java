package com.vrunk.vrunk_backend.entity;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.*;

@Entity
@Table(name = "collection")
public class Collection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "collection_id")
    private Long collection_id;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "collection", cascade = CascadeType.ALL)
    private Set<Produit> produit = new HashSet<>();

    // Getters et setters
    public Long getId() {
        return collection_id;
    }

    public void setId(Long collection_id) {
        this.collection_id = collection_id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Produit> getProduit() {
        return produit;
    }

    public void setProduit(Set<Produit> produit) {
        this.produit = produit;
    }
}
