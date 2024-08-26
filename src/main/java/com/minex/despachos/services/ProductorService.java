package com.minex.despachos.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.minex.despachos.models.Productor;
import com.minex.despachos.repositories.ProductorRepository;

@Service
public class ProductorService {
    private final ProductorRepository productorRepository;

    public ProductorService(ProductorRepository productorRepository) {
        this.productorRepository = productorRepository;
    }

    public Productor save(Productor productor) {
        return productorRepository.save(productor);
    }

    public void deleteById(Long id) {
        productorRepository.deleteById(id);
    }

    public Productor getById(Long id) {
        return productorRepository.findById(id).orElse(null);
    }

    public List<Productor> getAll() {
        return (List<Productor>) productorRepository.findAll();
    }

    public boolean existsByRut(String rut) {
        return productorRepository.existsByRut(rut);
    }
}
