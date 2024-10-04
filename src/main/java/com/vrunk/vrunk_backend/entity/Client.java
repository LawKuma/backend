package com.vrunk.vrunk_backend.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "statut_compte")
    private String statutCompte;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "adresse_id", referencedColumnName = "adresse_id")
    private Adresse adresse;  
    
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private Set<Commande> commandes = new HashSet<>();

    // Constructeur par défaut
    public Client() {
    }

    // Constructeur avec paramètres
    public Client(Long clientId, String email, String nom, String prenom, String telephone, Adresse adresse, String statutCompte, String password) {
        this.clientId = clientId;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.adresse = adresse;
        this.statutCompte = statutCompte;
        this.password = password;
    }

    // Getters et Setters
    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getStatutCompte() {
        return statutCompte;
    }

    public void setStatutCompte(String statutCompte) {
        this.statutCompte = statutCompte;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Set<Commande> getCommande() {
        return commandes;
    }

    public void setCommande(Set<Commande> commandes) {
        this.commandes = commandes;
    }

    // Méthode pour ajouter une commande
    public void addCommande(Commande commande) {
        if (commande != null) {
            if (commandes == null) {
                commandes = new HashSet<>();
            }
            commandes.add(commande);
            commande.setClient(this);
        }
    }
}
