package com.vrunk.vrunk_backend.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "commande")
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long commandeId;

    @Column(name = "numero_suivi_commande")
    private String numeroSuiviCommande;

    @Column(name = "quantite_totale")
    private int quantiteTotale;

    @Column(name = "prix_total")
    private BigDecimal prixTotal;

    @Column(name = "statut")
    private String statut;

    @Column(name = "date_creation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;

    @Column(name = "derniere_mise_a_jour")
    @Temporal(TemporalType.TIMESTAMP)
    private Date derniereMiseAJour;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commande")
    private Set<DetailsCommande> detailsCommandes = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "adresse_id", referencedColumnName = "adresse_id")
    private Adresse adresse;

    // Méthode pour ajouter un article à la commande avec la taille
    public void ajouterArticle(DetailsCommande detailsCommande, String taille) {
        if (detailsCommande != null) {
            if (detailsCommandes == null) {
                detailsCommandes = new HashSet<>();
            }
            detailsCommande.setTaille(taille); 
            detailsCommandes.add(detailsCommande);
            detailsCommande.setCommande(this);
            recalculerPrixTotal();  // Recalculer le prix total chaque fois qu'un article est ajouté
        }
    }

    // Méthode pour calculer le prix total de la commande
    public void recalculerPrixTotal() {
        prixTotal = BigDecimal.ZERO;
        for (DetailsCommande details : detailsCommandes) {
            BigDecimal prixArticle = details.getProduit().getPrix().multiply(BigDecimal.valueOf(details.getQuantite()));
            prixTotal = prixTotal.add(prixArticle);
        }
    }

    // Getters et Setters
    public Long getCommandeId() {
        return commandeId;
    }

    public void setCommandeId(Long commandeId) {
        this.commandeId = commandeId;
    }

    public String getNumeroSuiviCommande() {
        return numeroSuiviCommande;
    }

    public void setNumeroSuiviCommande(String numeroSuiviCommande) {
        this.numeroSuiviCommande = numeroSuiviCommande;
    }

    public int getQuantiteTotale() {
        return quantiteTotale;
    }

    public void setQuantiteTotale(int quantiteTotale) {
        this.quantiteTotale = quantiteTotale;
    }

    public BigDecimal getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(BigDecimal prixTotal) {
        this.prixTotal = prixTotal;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDerniereMiseAJour() {
        return derniereMiseAJour;
    }

    public void setDerniereMiseAJour(Date derniereMiseAJour) {
        this.derniereMiseAJour = derniereMiseAJour;
    }

    public Set<DetailsCommande> getDetailsCommandes() {
        return detailsCommandes;
    }

    public void setDetailsCommandes(Set<DetailsCommande> detailsCommandes) {
        this.detailsCommandes = detailsCommandes;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }
}
