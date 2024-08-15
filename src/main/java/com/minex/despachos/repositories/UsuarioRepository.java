package com.minex.despachos.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.minex.despachos.models.Usuario;
import com.minex.despachos.models.Usuario.Rol;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    Usuario findByRut(String rut);
    Usuario findByEmail(String email);
    List<Usuario> findByRol(Rol admin);
    
}
