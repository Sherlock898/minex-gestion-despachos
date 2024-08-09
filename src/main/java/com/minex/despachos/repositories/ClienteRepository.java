package com.minex.despachos.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.minex.despachos.models.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {
    Cliente findByRut(String rut);
    Cliente findByRazonSocial(String razonSocial);
    
}
