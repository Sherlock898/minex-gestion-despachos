package com.minex.despachos.config;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

import com.minex.despachos.models.Usuario;
import com.minex.despachos.repositories.UsuarioRepository;

import jakarta.annotation.PostConstruct;

// TODO: add env variable to data loader
@Component
public class DataLoader {
    private final UsuarioRepository usuarioRepository;

    public DataLoader(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostConstruct
    public void init(){
        createAdmin();
    }

    private void createAdmin(){
        List<Usuario> admins = usuarioRepository.findByRol(Usuario.Rol.ADMIN);
        if(admins.size() > 0) return;

        System.out.println("Creating admin");

        String emailPlaceHolder = "admin@admin";
        String passwordPlaceHolder = "admin";
        
        Usuario admin = new Usuario();
        admin.setEmail(emailPlaceHolder);
        admin.setPassword(BCrypt.hashpw(passwordPlaceHolder, BCrypt.gensalt()));
        admin.setRol(Usuario.Rol.ADMIN);
        admin.setNombre("admin");
        admin.setRut("11111111-1");

        System.out.println("Saving to database");

        usuarioRepository.save(admin);

        System.out.println("Admin created");
    }
}
