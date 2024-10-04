package com.vrunk.vrunk_backend.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.vrunk.vrunk_backend.dao.ClientRepository;
import com.vrunk.vrunk_backend.entity.Client;
import com.vrunk.vrunk_backend.service.ClientService;

public class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepository clientRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testInscrireClient_ClientDejaExistant() {
        // Arrange
        Client client = new Client();
        client.setEmail("john.doe@example.com");
    
        // Simuler un client existant
        when(clientRepository.findByEmail(client.getEmail())).thenReturn(Optional.of(client));
    
        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            clientService.inscrireClient(client);
        });
    
        // Afficher le message d'exception dans la console
        System.out.println("Message d'exception: " + exception.getMessage());
    
        // Vérifier que le message est correct
        assertEquals("Un client avec cet email n'existe pas ou existe déjà.", exception.getMessage());
    
        // Vérifier que la méthode save() n'est jamais appelée
        verify(clientRepository, never()).save(any(Client.class));
    }

    @Test
    public void testInscrireClient_NouveauClient() {
        // Arrange
        Client client = new Client();
        client.setEmail("new@example.com");

        // Simuler l'absence du client dans la base de données
        when(clientRepository.findByEmail(client.getEmail())).thenReturn(Optional.empty());

        // Simuler l'enregistrement du client
        when(clientRepository.save(client)).thenReturn(client);

        // Act
        Client result = clientService.inscrireClient(client);

        // Assert
        assertNotNull(result);
        assertEquals(client.getEmail(), result.getEmail());

        // Vérifie que la méthode save() est appelée une seule fois
        verify(clientRepository, times(1)).save(client);
    }
}
