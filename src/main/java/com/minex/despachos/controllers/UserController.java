package com.minex.despachos.controllers;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minex.despachos.models.Usuario;
import com.minex.despachos.services.UsuarioService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;



//TODO: Anyadir interceptor para verificar que el usuario esté logeado
@RestController
@RequestMapping("/api/usuarios")
@Validated
public class UserController {
    private final UsuarioService usuarioService;

    public UserController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Funciones de registro y login
    @GetMapping("/login")
    public String mostrarLogin(@ModelAttribute("usuario") Usuario _u, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if(usuario != null){
            return "redirect:/main";
        }
        return "login.jsp";
    }

    @PostMapping("/login")
    public String validarLogin(@ModelAttribute("usuario") Usuario usuario,
    @RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
        boolean isAutenticado = usuarioService.autenticarUsuario(email, password);
        if(isAutenticado){
            session.setAttribute("usuario", usuario);
            return "redirect:/main";
        }
        else{
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "login.jsp";
        }
    }

    @GetMapping("/registro")
    public String mostrarRegistro(@ModelAttribute("usuario") Usuario _u, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if(usuario == null){
            return "redirect:/main";
        }
        else if(usuario.getRol() != Usuario.Rol.ADMIN){
            return "redirect:/main";
        }
        return "registro.jsp";
    }

    @PostMapping("/registro")
    public String registrarUsuario(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result, Model model, HttpSession session) {
        Usuario usuarioSesion = (Usuario) session.getAttribute("usuario");
        if(usuarioSesion == null){
            return "redirect:/main";
        }
        else if(usuarioSesion.getRol() != Usuario.Rol.ADMIN){
            return "redirect:/main";
        }
        Usuario emailRegistrado = usuarioService.getByEmail(usuario.getEmail());
        if(emailRegistrado != null){
            result.rejectValue("email", "unique", "Este email ya está registrado");
        }
        Usuario rutRegistrado = usuarioService.getByRut(usuario.getRut());
        if(rutRegistrado != null){
            result.rejectValue("rut", "unique", "Este rut ya está registrado");
        }
        if(!usuario.getPassword().equals(usuario.getPasswordConfirm())){
            result.rejectValue("passwordConfirm", "match", "Las contraseñas no coinciden");
        }
        
        if(result.hasErrors()){
            return "registro.jsp";
        }
        Usuario usuarioRegistradoExitoso = usuarioService.registrar(usuario);
        if(usuarioRegistradoExitoso == null){
            model.addAttribute("error", "Error, no se pudo registrar el usuario");
            return "registro.jsp";
        }
        usuarioService.registrar(usuario);
        return "registro.jsp";
    }    


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

}
