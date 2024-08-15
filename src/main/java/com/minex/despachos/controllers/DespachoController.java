package com.minex.despachos.controllers;


import com.minex.despachos.models.Despacho;
import com.minex.despachos.models.Usuario;
import com.minex.despachos.services.DespachoService;
import com.minex.despachos.services.UsuarioService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class DespachoController {
    private final DespachoService despachoService;
    private final UsuarioService usuarioService;

    public DespachoController(DespachoService despachoService, UsuarioService usuarioService) {
        this.despachoService = despachoService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/despachos")
    public String showDespachos(HttpSession session, Model model){
        Long usuarioId = (Long) session.getAttribute("usuarioId");
        if(usuarioId == null) return "redirect:/";
        Usuario usuario = usuarioService.getById(usuarioId);
        if(usuario == null) return "redirect:/";
        model.addAttribute("despachos", despachoService.getAll());
        return "/Despachos/Despachos.jsp";
    }
    
    @GetMapping("/despachos/add")
    public String addDespachoForm(HttpSession session, @ModelAttribute("despacho") Despacho _despacho){
        Long usuarioId = (Long) session.getAttribute("usuarioId");
        if(usuarioId == null) return "redirect:/";
        Usuario usuario = usuarioService.getById(usuarioId);
        if(usuario == null) return "redirect:/";
        if(usuario.getRol() != Usuario.Rol.ADMIN) return "redirect:/";
        return "/Despachos/AddDespacho.jsp";
    }

    @PostMapping("/despachos")
    public String addDespacho(HttpSession session, @Valid @ModelAttribute("despacho") Despacho despacho, BindingResult result){
        Long usuarioId = (Long) session.getAttribute("usuarioId");
        if(usuarioId == null) return "redirect:/";
        Usuario usuario = usuarioService.getById(usuarioId);
        if(usuario == null) return "redirect:/";
        if(usuario.getRol() != Usuario.Rol.ADMIN) return "redirect:/";
        if(result.hasErrors()) return "/Despachos/AddDespacho.jsp";
        despachoService.save(despacho);
        return "redirect:/despachos";
    }
}
