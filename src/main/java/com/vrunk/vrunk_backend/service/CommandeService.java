package com.vrunk.vrunk_backend.service;

import com.stripe.exception.StripeException;
import com.vrunk.vrunk_backend.dto.InfosPaiement;
import com.vrunk.vrunk_backend.dto.ReponseAchat;
import com.vrunk.vrunk_backend.dto.Achat;

public interface CommandeService {

    ReponseAchat passerCommande(Achat achat);

    Object creerIntentPaiement(InfosPaiement infosPaiement) throws StripeException;
}
