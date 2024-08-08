package com.minex.despachos.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.minex.despachos.models.Camion;

@Repository
public interface CamionRepository extends CrudRepository<Camion, String> {
    
}
