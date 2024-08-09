package com.minex.despachos.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.minex.despachos.models.Chofer;
import com.minex.despachos.repositories.ChoferRepository;

@Service
public class ChoferService {
    private final ChoferRepository choferRepository;

    public ChoferService(ChoferRepository choferRepository) {
        this.choferRepository = choferRepository;
    }

    public Chofer save(Chofer chofer) {
        return choferRepository.save(chofer);
    }

    public void deleteById(Long id) {
        choferRepository.deleteById(id);
    }

    public Chofer getById(Long id) {
        return choferRepository.findById(id).orElse(null);
    }

    public List<Chofer> getAll() {
        return (List<Chofer>) choferRepository.findAll();
    }

    public List<Chofer> getActivos() {
        return choferRepository.findByActivoTrue();
    }
}
