package com.vrunk.vrunk_backend.controller;

import com.vrunk.vrunk_backend.entity.Client;
import com.vrunk.vrunk_backend.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/inscription")
    public ResponseEntity<Client> inscription(@RequestBody Client client) {

        Client nouveauClient = clientService.inscrireClient(client);

        return new ResponseEntity<>(nouveauClient, HttpStatus.CREATED);
    }
}