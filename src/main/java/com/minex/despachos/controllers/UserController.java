package com.minex.despachos.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import com.minex.despachos.models.Usuario;
import com.minex.despachos.services.UsuarioService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

//TODO: Anyadir interceptor para verificar que el usuario esté logeado
@Controller
public class UserController {
    private final UsuarioService usuarioService;

    public UserController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/")
    public String index(HttpSession session) {
        Long usuarioId = (Long) session.getAttribute("usuarioId");
        if (usuarioId == null) {
            return "redirect:/login";
        }
        Usuario usuario = usuarioService.getById(usuarioId);
        if (usuario == null) {
            return "redirect:/login";
        }
        return "Index.jsp";
    }

    // Funciones de registro y login
    @GetMapping("/login")
    public String mostrarLogin(@ModelAttribute("usuario") Usuario _u, HttpSession session) {
        Long usuarioId = (Long) session.getAttribute("usuario");
        if (usuarioId != null) {
            Usuario usuario = usuarioService.getById(usuarioId);
            if (usuario != null) {
                return "redirect:/";
            }
            return "redirect:/";
        }

        return "/Usuario/Login.jsp";
    }

    @PostMapping("/login")
    public String validarLogin(@ModelAttribute("usuario") Usuario _usuario,
            @RequestParam("email") String email, @RequestParam("password") String password, Model model,
            HttpSession session) {
        boolean isAutenticado = usuarioService.autenticarUsuario(email, password);
        if (isAutenticado) {
            Usuario usuario = usuarioService.getByEmail(email);
            session.setAttribute("usuarioId", usuario.getId());
            return "redirect:/";
        } else {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "/Usuario/Login.jsp";
        }
    }

    @GetMapping("/registro")
    public String mostrarRegistro(@ModelAttribute("usuario") Usuario _u, HttpSession session) {
        Long usuarioId = (Long) session.getAttribute("usuarioId");
        if (usuarioId == null) {
            return "redirect:/";
        }
        Usuario usuario = usuarioService.getById(usuarioId);
        if (usuario == null) {
            return "redirect:/";
        } else if (usuario.getRol() != Usuario.Rol.ADMIN) {
            return "redirect:/";
        }

        return "/Usuario/Registro.jsp";
    }

    @PostMapping("/registro")
    public String registrarUsuario(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result, Model model, HttpSession session) {     
        Long usuarioId = (Long) session.getAttribute("usuarioId");
        if (usuarioId == null) return "redirect:/";
        Usuario usuarioSesion = usuarioService.getById(usuarioId);
        if (usuarioSesion == null) return "redirect:/";
        if (usuarioSesion.getRol() != Usuario.Rol.ADMIN) return "redirect:/";
        Usuario emailRegistrado = usuarioService.getByEmail(usuario.getEmail());
        if (emailRegistrado != null) {
            result.rejectValue("email", "unique", "Este email ya está registrado");
        }
        Usuario rutRegistrado = usuarioService.getByRut(usuario.getRut());
        if (rutRegistrado != null) {
            result.rejectValue("rut", "unique", "Este rut ya está registrado");
        }
        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            return "/Usuario/Registro.jsp";
        }
        Usuario usuarioRegistradoExitoso = usuarioService.registrar(usuario);
        if (usuarioRegistradoExitoso == null) {
            model.addAttribute("error", "Error, no se pudo registrar el usuario");
            return "/Usuario/Registro.jsp";
        }
        usuarioService.registrar(usuario);
        return "redirect:/admin";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    // Admin page
    @GetMapping("/admin")
    public String adminPage(HttpSession session, Model model) {
        Long usuarioId = (Long) session.getAttribute("usuarioId");
        if (usuarioId == null) {
            return "redirect:/login";
        }
        Usuario usuario = usuarioService.getById(usuarioId);
        if (usuario == null) {
            return "redirect:/login";
        }
        if (usuario.getRol() != Usuario.Rol.ADMIN) {
            return "redirect:/";
        }
        model.addAttribute("usuarios", usuarioService.getAll());
        model.addAttribute("usuario", usuario);
        return "/Usuario/Admin.jsp";
    }


}
