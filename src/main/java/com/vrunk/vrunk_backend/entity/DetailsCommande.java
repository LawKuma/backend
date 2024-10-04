package com.vrunk.vrunk_backend.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "details_commande")
public class DetailsCommande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "details_commande")
    private Long detailsCommandeId;

    @Column(name = "quantite")
    private int quantite;

    @Column(name = "taille")
    private String taille;

    @Column(name = "prix_unite")
    private BigDecimal prixUnite;

    @ManyToOne
    @JoinColumn(name = "produit_id")
    private Produit produit;

    @ManyToOne
    @JoinColumn(name = "commande_id")
    private Commande commande;

    // Getters et Setters

    public Long getDetailsCommandeId() {
        return detailsCommandeId;
    }

    public void setDetailsCommandeId(Long detailsCommandeId) {
        this.detailsCommandeId = detailsCommandeId;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    public BigDecimal getPrixUnite() {
        return prixUnite;
    }

    public void setPrixUnite(BigDecimal prixUnite) {
        this.prixUnite = prixUnite;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }
}
