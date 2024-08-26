package com.minex.despachos.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.minex.despachos.models.Despacho;

@Repository
public interface DespachoRepository extends CrudRepository<Despacho, Long> {

    List<Despacho> findByClienteId(Long idCliente);
    List<Despacho> findByDespachoCamionIsNull();
    
}
