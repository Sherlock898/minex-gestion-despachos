package com.minex.despachos.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.minex.despachos.models.Productor;

@Repository
public interface ProductorRepository extends CrudRepository<Productor, Long> {
    Productor findByRut(String rut);
    Productor findByRazonSocial(String razonSocial);
    
}
