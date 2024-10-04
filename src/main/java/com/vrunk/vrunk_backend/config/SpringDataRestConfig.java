package com.vrunk.vrunk_backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import com.vrunk.vrunk_backend.entity.*;

import jakarta.persistence.EntityManager;

import java.util.ArrayList;

@Configuration
public class SpringDataRestConfig implements RepositoryRestConfigurer {

    @Value("${allowed.origins}")
    private String[] allowedOrigins;

    private final EntityManager entityManager;

    public SpringDataRestConfig(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {

        HttpMethod[] unsupportedActions = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE, HttpMethod.PATCH};

        // Désactivation des méthodes HTTP : PUT, POST, DELETE, PATCH
        disableHttpMethods(Produit.class, config, unsupportedActions);
        disableHttpMethods(Categorie.class, config, unsupportedActions);
        disableHttpMethods(Client.class, config, unsupportedActions);
        disableHttpMethods(Commande.class, config, unsupportedActions);
        disableHttpMethods(DetailsCommande.class, config, unsupportedActions);
        disableHttpMethods(Admin.class, config, unsupportedActions);

        // Appel à la méthode interne pour exposer les IDs
        exposeIds(config);

        // Configuration du CORS
        cors.addMapping(config.getBasePath() + "/**").allowedOrigins(allowedOrigins);
    }

    private static void disableHttpMethods(Class<?> theClass, RepositoryRestConfiguration config, HttpMethod[] unsupportedActions) {
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure(((metadata, httpMethods) -> httpMethods.disable(unsupportedActions)))
                .withCollectionExposure(((metadata, httpMethods) -> httpMethods.disable(unsupportedActions)));
    }

    private void exposeIds(RepositoryRestConfiguration config) {
        // Exposer les identifiants des entités

        // Obtenir la liste de toutes les entités à partir de l'EntityManager
        var entities = entityManager.getMetamodel().getEntities();

        // Créer un tableau des types d'entité
        var entityClasses = new ArrayList<Class<?>>();

        // Obtenir les types d'entité pour chaque entité
        for (var entity : entities) {
            entityClasses.add(entity.getJavaType());
        }

        // Exposer les identifiants pour ces types d'entités
        var domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }
}
