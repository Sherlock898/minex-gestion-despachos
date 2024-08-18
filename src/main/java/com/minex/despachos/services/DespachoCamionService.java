package com.minex.despachos.services;

import org.springframework.stereotype.Service;

import com.minex.despachos.models.DespachoCamion;
import com.minex.despachos.repositories.DespachoCamionRepository;

@Service
public class DespachoCamionService {
    private final DespachoCamionRepository despachoCamionRepository;

    public DespachoCamionService(DespachoCamionRepository despachoCamionRepository) {
        this.despachoCamionRepository = despachoCamionRepository;
    }

    public DespachoCamion save(DespachoCamion despachoCamion) {
        return despachoCamionRepository.save(despachoCamion);
    }

    public void deleteById(Long id) {
        despachoCamionRepository.deleteById(id);
    }

    public void getAll() {
        despachoCamionRepository.findAll();
    }
}
