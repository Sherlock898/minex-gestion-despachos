package com.minex.despachos.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.minex.despachos.models.Cliente;
import com.minex.despachos.repositories.ClienteRepository;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }

    public List<Cliente> getAll() {
        return (List<Cliente>) clienteRepository.findAll();
    }
}
