package com.vrunk.vrunk_backend.dto;

import com.vrunk.vrunk_backend.entity.Adresse;
import com.vrunk.vrunk_backend.entity.Client;
import com.vrunk.vrunk_backend.entity.Commande;
import com.vrunk.vrunk_backend.entity.DetailsCommande;
import java.util.Set;

public class Achat {

    private Client client;

    private Adresse adresse;

    private Commande commande;

    private Set<DetailsCommande> detailsCommandes;

    // Getters et Setters
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

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Set<DetailsCommande> getDetailsCommandes() {
        return detailsCommandes;
    }

    public void setDetailsCommandes(Set<DetailsCommande> detailsCommandes) {
        this.detailsCommandes = detailsCommandes;
    }
}
