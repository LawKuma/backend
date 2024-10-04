package com.vrunk.vrunk_backend.dto;

public class ReponseAchat {

    private final String numeroSuiviCommande;

    public ReponseAchat(String numeroSuiviCommande) {
        this.numeroSuiviCommande = numeroSuiviCommande;
    }

    public String getNumeroSuiviCommande() {
        return numeroSuiviCommande;
    }
}
