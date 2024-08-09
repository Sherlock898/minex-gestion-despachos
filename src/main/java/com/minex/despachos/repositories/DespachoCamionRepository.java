package com.minex.despachos.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.minex.despachos.models.DespachoCamion;
import com.minex.despachos.models.DespachoCamionPK;

@Repository
public interface DespachoCamionRepository extends CrudRepository<DespachoCamion, DespachoCamionPK> {
    DespachoCamion findByDespachoId(Long idDespacho);
    DespachoCamion findByCamionPatente(String patenteCamion);
}
