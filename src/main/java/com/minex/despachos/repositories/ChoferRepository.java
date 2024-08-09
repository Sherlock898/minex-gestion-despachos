package com.minex.despachos.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.minex.despachos.models.Chofer;

@Repository
public interface ChoferRepository extends CrudRepository<Chofer, Long> {
    List<Chofer> findByActivoTrue();
    
}
