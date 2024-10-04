package com.vrunk.vrunk_backend.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vrunk.vrunk_backend.dao.ClientRepository;
import com.vrunk.vrunk_backend.entity.Client;
import jakarta.transaction.Transactional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional
    public Client inscrireClient(Client client) {

        // Vérifier si le client existe déjà
        Optional<Client> existingClient = clientRepository.findByEmail(client.getEmail());

        if (existingClient.isPresent()) {
            throw new RuntimeException("Un client avec cet email n'existe pas ou existe déjà.");
        }

        // Sauvegarder le nouveau client dans la base de données
        return clientRepository.save(client);
    }
}
