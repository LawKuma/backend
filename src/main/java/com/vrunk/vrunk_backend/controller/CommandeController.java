package com.vrunk.vrunk_backend.controller;

import com.stripe.exception.StripeException;
import com.vrunk.vrunk_backend.dto.Achat;
import com.vrunk.vrunk_backend.dto.InfosPaiement;
import com.vrunk.vrunk_backend.dto.ReponseAchat;
import com.vrunk.vrunk_backend.service.CommandeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/commande")
public class CommandeController {

    private final CommandeService commandeService;

    public CommandeController(CommandeService commandeService) {
        this.commandeService = commandeService;
    }

    // Endpoint pour placer une commande
    @PostMapping("/achat")
    public ReponseAchat passerCommande(@RequestBody Achat achat) {
        return commandeService.passerCommande(achat);
    }

    // Endpoint pour créer une intention de paiement via Stripe
    @PostMapping("/intent-paiement")
    public ResponseEntity<Object> creerIntentPaiement(@RequestBody InfosPaiement infosPaiement) throws StripeException {
        Object paymentIntent = commandeService.creerIntentPaiement(infosPaiement);
        return new ResponseEntity<>(paymentIntent, HttpStatus.OK);
    }
}
