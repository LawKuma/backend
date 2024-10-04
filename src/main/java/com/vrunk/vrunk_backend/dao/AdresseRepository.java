package com.vrunk.vrunk_backend.dao;

import com.vrunk.vrunk_backend.entity.Adresse;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AdresseRepository extends JpaRepository<Adresse, Long> {

    List<Adresse> findByVille(String ville);

    List<Adresse> findByCodePostal(String codePostal);

    List<Adresse> findByPays(String pays);
}
