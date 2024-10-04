package com.vrunk.vrunk_backend.dao;

import com.vrunk.vrunk_backend.entity.Collection;
import java.util.List; 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Long> {
    
    List<Collection> findByNom(String nom);
}
