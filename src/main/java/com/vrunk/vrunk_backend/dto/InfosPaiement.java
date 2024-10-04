package com.vrunk.vrunk_backend.dto;

public class InfosPaiement {

    private int montant;
    private String devise;
    private String emailRecu;

    // Getters et Setters
    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public String getDevise() {
        return devise;
    }

    public void setDevise(String devise) {
        this.devise = devise;
    }

    public String getEmailRecu() {
        return emailRecu;
    }

    public void setEmailRecu(String emailRecu) {
        this.emailRecu = emailRecu;
    }
}
