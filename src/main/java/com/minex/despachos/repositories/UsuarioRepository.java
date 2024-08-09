package com.minex.despachos.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.minex.despachos.models.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    Usuario findByRut(String rut);
    Usuario findByEmail(String email);
    
}
