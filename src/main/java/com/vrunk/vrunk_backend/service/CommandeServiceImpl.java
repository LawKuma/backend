package com.vrunk.vrunk_backend.service;

import com.stripe.Stripe;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.vrunk.vrunk_backend.dao.ClientRepository;
import com.vrunk.vrunk_backend.dto.InfosPaiement;
import com.vrunk.vrunk_backend.dto.ReponseAchat;
import com.vrunk.vrunk_backend.dto.Achat;
import com.vrunk.vrunk_backend.entity.Client;
import com.vrunk.vrunk_backend.entity.Commande;
import com.vrunk.vrunk_backend.exception.ClientNotFoundException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class CommandeServiceImpl implements CommandeService {

    private final ClientRepository clientRepository;

    public CommandeServiceImpl(ClientRepository clientRepository, @Value("${stripe.key.secret}") String secretKey) {
        this.clientRepository = clientRepository;

        // Initialiser l'API Stripe avec la clé secrète
        Stripe.apiKey = secretKey;
    }

    @Override
    @Transactional
    public ReponseAchat passerCommande(Achat achat) {
        // Récupérer les infos de la commande
        Commande commande = achat.getCommande();

        // Générer un numéro de suivi pour la commande
        String numeroSuiviCommande = genererNumeroSuivi();
        commande.setNumeroSuiviCommande(numeroSuiviCommande);

        // Associer la commande au client
        Client client = achat.getClient();
        String email = client.getEmail();
        Optional<Client> clientFromDB = clientRepository.findByEmail(email);
    
        if (clientFromDB.isPresent()) {
            client = clientFromDB.get();
        } else {
        // Si le client n'existe pas, lancer une exception ou rediriger
        throw new ClientNotFoundException("Le client avec l'email " + email + " n'existe pas. Veuillez vous inscrire.");
        }

        client.addCommande(commande);

        // Enregistrer dans la base de données
        clientRepository.save(client);

        // Retourner la réponse
        return new ReponseAchat(numeroSuiviCommande);
    }

    @Override
    public Object creerIntentPaiement(InfosPaiement infosPaiement) {
        Map<String, Object> params = new HashMap<>();
        params.put("amount", infosPaiement.getMontant());
        params.put("currency", infosPaiement.getDevise());
        params.put("payment_method_types", new ArrayList<String>() {{
            add("card");
        }});
        params.put("description", "Achat Vrunk");
        params.put("receipt_email", infosPaiement.getEmailRecu());

        try {
            // Création de l'intention de paiement
            return PaymentIntent.create(params);
        } catch (CardException e) {
            // Gestion spécifique de l'erreur de carte refusée
            return "Votre carte a été refusée.";
        } catch (InvalidRequestException e) {
            // Gestion des paramètres incorrects
            return "Une erreur est survenue lors du traitement de votre paiement.";
        } catch (StripeException e) {
            // Gestion générale d'autres erreurs Stripe
            return "Une erreur s'est produite avec le service de paiement.";
        }
    }

    private String genererNumeroSuivi() {
        // Générer un numéro de suivi aléatoire avec UUID (version-4)
        return UUID.randomUUID().toString();
    }
}
