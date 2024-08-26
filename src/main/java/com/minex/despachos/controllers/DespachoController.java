package com.minex.despachos.controllers;


import com.minex.despachos.models.Despacho;
import com.minex.despachos.models.DespachoCamion;
import com.minex.despachos.models.Usuario;
import com.minex.despachos.services.CamionService;
import com.minex.despachos.services.ChoferService;
import com.minex.despachos.services.ClienteService;
import com.minex.despachos.services.DespachoCamionService;
import com.minex.despachos.services.DespachoService;
import com.minex.despachos.services.ProductorService;
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
    private final DespachoCamionService despachoCamionService;
    private final ClienteService clienteService;
    private final ProductorService productorService;
    private final CamionService camionService;
    private final ChoferService choferService;
    private final UsuarioService usuarioService;

    public DespachoController(DespachoService despachoService, DespachoCamionService despachoCamionService, UsuarioService usuarioService, ClienteService clienteService, ProductorService productorService, CamionService camionService, ChoferService choferService) {
        this.despachoService = despachoService;
        this.despachoCamionService = despachoCamionService;
        this.usuarioService = usuarioService;
        this.clienteService = clienteService;
        this.productorService = productorService;
        this.camionService = camionService;
        this.choferService = choferService;
    }

    @GetMapping("/despachos")
    public String showDespachos(HttpSession session, Model model){
        Long usuarioId = (Long) session.getAttribute("usuarioId");
        if(usuarioId == null) return "redirect:/";
        Usuario usuario = usuarioService.getById(usuarioId);
        if(usuario == null) return "redirect:/";
        model.addAttribute("despachos", despachoService.getAll());
        model.addAttribute("usuario", usuario);
        return "/Despachos/Despachos.jsp";
    }
    
    @GetMapping("/despachos/add")
    public String addDespachoForm(HttpSession session, @ModelAttribute("despacho") Despacho _despacho, Model model){
        Long usuarioId = (Long) session.getAttribute("usuarioId");
        if(usuarioId == null) return "redirect:/";
        Usuario usuario = usuarioService.getById(usuarioId);
        if(usuario == null) return "redirect:/";
        if(usuario.getRol() != Usuario.Rol.ADMIN) return "redirect:/";
        model.addAttribute("usuario", usuario);
        model.addAttribute("clientes", clienteService.getAll());
        model.addAttribute("productores", productorService.getAll());
        model.addAttribute("unidades", Despacho.UnidadDeMedida.values());
        return "/Despachos/DespachosFormulario.jsp";
    }

    @PostMapping("/despachos/add")
    public String addDespacho(@Valid @ModelAttribute("despacho") Despacho despacho, BindingResult result, HttpSession session, Model model){
        Long usuarioId = (Long) session.getAttribute("usuarioId");
        if(usuarioId == null) return "redirect:/";
        Usuario usuario = usuarioService.getById(usuarioId);
        if(usuario == null) return "redirect:/";
        if(usuario.getRol() != Usuario.Rol.ADMIN) return "redirect:/";
        if(result.hasErrors()){
            model.addAttribute("usuario", usuario);
            model.addAttribute("clientes", clienteService.getAll());
            model.addAttribute("productores", productorService.getAll());
            model.addAttribute("unidades", Despacho.UnidadDeMedida.values());
            return "/Despachos/DespachosFormulario.jsp";
        }
        despachoService.save(despacho);
        return "redirect:/despachos";
    }

    // Despacho Camion, Esta es la parte del control
    @GetMapping("/despachos/control")
    public String showDespachosControl(@ModelAttribute("despachoCamion") DespachoCamion _despachoCamion, HttpSession session, Model model){
        Long usuarioId = (Long) session.getAttribute("usuarioId");
        if(usuarioId == null) return "redirect:/";
        Usuario usuario = usuarioService.getById(usuarioId);
        if(usuario == null) return "redirect:/";
        model.addAttribute("despachos", despachoService.getNoAsignados());
        model.addAttribute("camiones", camionService.getActivos());
        model.addAttribute("choferes", choferService.getActivos());
        model.addAttribute("usuario", usuario);
        return "/Despachos/DespachosControl.jsp";
    }

    @PostMapping("/despachos/control")
    public String controlDespacho(@Valid @ModelAttribute("despachoCamion") DespachoCamion despachoCamion, BindingResult result, HttpSession session, Model model){    
        Long usuarioId = (Long) session.getAttribute("usuarioId");
        if(usuarioId == null) return "redirect:/";
        Usuario usuario = usuarioService.getById(usuarioId);
        if(usuario == null) return "redirect:/";
        if(usuario.getRol() != Usuario.Rol.ADMIN) return "redirect:/";
        if(result.hasErrors()){
            model.addAttribute("usuario", usuario);
            model.addAttribute("despachos", despachoService.getNoAsignados());
            model.addAttribute("camiones", camionService.getActivos());
            model.addAttribute("choferes", choferService.getActivos());
            return "/Despachos/DespachosControl.jsp";
        }
        
        despachoCamionService.save(despachoCamion);
        return "redirect:/despachos/control";
    }
}