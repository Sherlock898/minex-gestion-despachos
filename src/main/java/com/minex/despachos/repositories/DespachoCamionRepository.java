package com.minex.despachos.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.minex.despachos.models.DespachoCamion;

@Repository
public interface DespachoCamionRepository extends CrudRepository<DespachoCamion, Long> {
    DespachoCamion findByDespachoId(Long idDespacho);
    DespachoCamion findByCamionPatente(String patenteCamion);
}
