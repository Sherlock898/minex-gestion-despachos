package com.minex.despachos.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.minex.despachos.models.Camion;
import com.minex.despachos.repositories.CamionRepository;

@Service
public class CamionService {
    private final CamionRepository camionRepository;

    public CamionService(CamionRepository camionRepository) {
        this.camionRepository = camionRepository;
    }

    public Camion save(Camion camion) {
        return camionRepository.save(camion);
    }

    public void deleteById(Long id) {
        camionRepository.deleteById(id);
    }

    public Camion getById(Long id) {
        return camionRepository.findById(id).orElse(null);
    }

    public List<Camion> getAll() {
        return (List<Camion>) camionRepository.findAll();
    }

    public List<Camion> getActivos() {
        return camionRepository.findByActivoTrue();
    }

    public boolean existsByPatente(String patente) {
        return camionRepository.existsByPatente(patente);
    }    
}
