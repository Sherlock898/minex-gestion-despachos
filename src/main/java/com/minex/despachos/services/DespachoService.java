package com.minex.despachos.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.minex.despachos.models.Despacho;
import com.minex.despachos.repositories.DespachoRepository;

@Service
public class DespachoService {
    private final DespachoRepository despachoRepository;

    public DespachoService(DespachoRepository despachoRepository) {
        this.despachoRepository = despachoRepository;
    }

    public Despacho save(Despacho despacho) {
        return despachoRepository.save(despacho);
    }

    public void deleteById(Long id) {
        despachoRepository.deleteById(id);
    }

    public Despacho getById(Long id) {
        return despachoRepository.findById(id).orElse(null);
    }

    public List<Despacho> getAll() {
        return (List<Despacho>) despachoRepository.findAll();
    }

    public List<Despacho> getByCliente(Long idCliente) {
        return despachoRepository.findByClienteId(idCliente);
    }

    public List<Despacho> getByProductor(Long idProductor){
        return despachoRepository.findByProductorId(idProductor);
    }

}
