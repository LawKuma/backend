package com.vrunk.vrunk_backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    // Les origines autorisées pour les requêtes CORS
    @Value("${allowed.origins}")
    private String[] allowedOrigins;

    // Le chemin de base pour les endpoints REST
    @Value("${spring.data.rest.base-path}")
    private String basePath;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // ou "/**" pour tous les endpoints
                .allowedOrigins("http://localhost:4200", "https://votre-site-angular.com") // URL du frontend Angular
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Méthodes autorisées
                .allowedHeaders("*") // Autoriser tous les headers
                .allowCredentials(true); // Autoriser les cookies/sessions si nécessaire
    }
}