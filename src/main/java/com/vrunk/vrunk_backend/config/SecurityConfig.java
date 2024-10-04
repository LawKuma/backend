package com.vrunk.vrunk_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/api/commandes/**").authenticated()  // Utilise requestMatchers
            )
            .oauth2ResourceServer(oauth2 -> oauth2
                .jwt() // Nouvelle méthode pour configurer JWT
            );

        // Ajout des filtres CORS
        http.cors();

        // Ajout de la stratégie de négociation de contenu
        http.setSharedObject(ContentNegotiationStrategy.class, new HeaderContentNegotiationStrategy());

        // Désactiver CSRF car l'application utilise des tokens
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }
}
