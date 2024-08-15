package com.minex.despachos.services;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.minex.despachos.models.Usuario;
import com.minex.despachos.repositories.UsuarioRepository;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario registrar(Usuario usuario) {
        String hashedPassword = BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt());
        usuario.setPassword(hashedPassword);
        return usuarioRepository.save(usuario);
    }

    public boolean autenticarUsuario(String email, String password){
        Usuario usuarioAutenticando = usuarioRepository.findByEmail(email);
        if(usuarioAutenticando == null){
            return false;
        }
        return BCrypt.checkpw(password, usuarioAutenticando.getPassword());
    }

    public Usuario actualizarPassword(String email, String password){
        Usuario usuario = usuarioRepository.findByEmail(email);
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        usuario.setPassword(hashedPassword);
        return usuarioRepository.save(usuario);
    }

    public void deleteById(Long id){
        usuarioRepository.deleteById(id);
    }

    public Usuario getByEmail(String email){
        return usuarioRepository.findByEmail(email);
    }

    public Usuario getByRut(String rut){
        return usuarioRepository.findByRut(rut);
    }

    public Usuario getById(Long usuarioId) {
        return usuarioRepository.findById(usuarioId).orElse(null);
    }
}
