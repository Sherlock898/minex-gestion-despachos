package com.minex.despachos.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import com.minex.despachos.models.Camion;
import com.minex.despachos.models.Chofer;
import com.minex.despachos.models.Cliente;
import com.minex.despachos.models.Productor;
import com.minex.despachos.models.Usuario;
import com.minex.despachos.services.CamionService;
import com.minex.despachos.services.ChoferService;
import com.minex.despachos.services.ClienteService;
import com.minex.despachos.services.ProductorService;
import com.minex.despachos.services.UsuarioService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Validated
public class TercerosController {
    private final CamionService camionService;
    private final ChoferService choferService;
    private final ProductorService productorService;
    private final ClienteService clienteService;
    private final UsuarioService usuarioService;

    public TercerosController(CamionService camionService, ChoferService choferService, ProductorService productorService,
            ClienteService clienteService, UsuarioService usuarioService) {
        this.camionService = camionService;
        this.choferService = choferService;
        this.productorService = productorService;
        this.clienteService = clienteService;
        this.usuarioService = usuarioService;
    }

    // Camion endpoints, abiertos para todos
    @GetMapping("/camiones")
    public String showCamiones(HttpSession session, Model model){
        Long usuarioId = (Long) session.getAttribute("usuarioId");
        if(usuarioId == null) return "redirect:/login";
        Usuario usuario = usuarioService.getById(usuarioId);
        if(usuario == null) return "redirect:/login";
        model.addAttribute("camiones", camionService.getAll());
        return "/Terceros/Camiones.jsp";
    }

    @GetMapping("/camiones/add")
    public String addCamionForm(@ModelAttribute("camion") Camion _camion, HttpSession session) {
        Long usuarioId = (Long) session.getAttribute("usuarioId");
        if(usuarioId == null) return "redirect:/login";
        Usuario usuario = usuarioService.getById(usuarioId);
        if(usuario == null) return "redirect:/login";
        return "/Terceros/CamionesFormulario.jsp";
    }

    @PostMapping("/camiones")
    public String addCamion(@Valid @ModelAttribute("camion") Camion camion, BindingResult result, HttpSession session) {
        Long usuarioId = (Long) session.getAttribute("usuarioId");
        if(usuarioId == null) return "redirect:/login";
        Usuario usuario = usuarioService.getById(usuarioId);
        if(usuario == null) return "redirect:/login";
        if(result.hasErrors()) return "/Terceros/CamionesFormulario.jsp";
        camionService.save(camion);
        return "redirect:/camiones";
    }

    // TODO: edit and delete endpoints

    // Chofer endpoints, abiertos para todos
    @GetMapping("/choferes")
    public String showChoferes(HttpSession session, Model model){
        Long usuarioId = (Long) session.getAttribute("usuarioId");
        if(usuarioId == null) return "redirect:/login";
        Usuario usuario = usuarioService.getById(usuarioId);
        if(usuario == null) return "redirect:/login";
        model.addAttribute("choferes", choferService.getAll());
        return "/Terceros/Choferes.jsp";
    }

    @GetMapping("/choferes/add")
    public String addChoferForm(@ModelAttribute("chofer") Chofer _chofer, HttpSession session) {
        Long usuarioId = (Long) session.getAttribute("usuarioId");
        if(usuarioId == null) return "redirect:/login";
        Usuario usuario = usuarioService.getById(usuarioId);
        if(usuario == null) return "redirect:/login";
        return "/Terceros/ChoferesFormulario.jsp";
    }

    @PostMapping("/choferes")
    public String addChofer(@Valid @ModelAttribute("chofer") Chofer chofer, BindingResult result, HttpSession session) {
        Long usuarioId = (Long) session.getAttribute("usuarioId");
        if(usuarioId == null) return "redirect:/login";
        Usuario usuario = usuarioService.getById(usuarioId);
        if(usuario == null) return "redirect:/login";
        if(result.hasErrors()) return "/Terceros/ChoferesFormulario.jsp";
        choferService.save(chofer);
        return "redirect:/choferes";
    }
    
    // TODO: edit and delete endpoints

    // Productor endpoints, post solo admins
    @GetMapping("/productores")
    public String showProductores(HttpSession session, Model model){
        Long usuarioId = (Long) session.getAttribute("usuarioId");
        if(usuarioId == null) return "redirect:/login";
        Usuario usuario = usuarioService.getById(usuarioId);
        if(usuario == null) return "redirect:/login";
        model.addAttribute("productores", productorService.getAll());
        return "/Terceros/Productores.jsp";
    }

    @GetMapping("/productores/add")
    public String addProductorForm(@ModelAttribute("productor") Productor _productor, HttpSession session) {
        Long usuarioId = (Long) session.getAttribute("usuarioId");
        if(usuarioId == null) return "redirect:/login";
        Usuario usuario = usuarioService.getById(usuarioId);
        if(usuario == null) return "redirect:/login";
        if(usuario.getRol() != Usuario.Rol.ADMIN) return "redirect:/";
        return "/Terceros/ProductoresFormulario.jsp";
    }

    @PostMapping("/productores")
    public String addProductor(@Valid @ModelAttribute("productor") Productor productor, BindingResult result, HttpSession session) {
        Long usuarioId = (Long) session.getAttribute("usuarioId");
        if(usuarioId == null) return "redirect:/login";
        Usuario usuario = usuarioService.getById(usuarioId);
        if(usuario == null) return "redirect:/login";
        if(usuario.getRol() != Usuario.Rol.ADMIN) return "redirect:/";
        if(result.hasErrors()) return "/Terceros/ProductoresFormulario.jsp";
        productorService.save(productor);
        return "redirect:/productores";
    }

    // TODO: edit and delete endpoints

    // Cliente endpoints, post solo admins
    @GetMapping("/clientes")
    public String showClientes(HttpSession session, Model model){
        Long usuarioId = (Long) session.getAttribute("usuarioId");
        if(usuarioId == null) return "redirect:/login";
        Usuario usuario = usuarioService.getById(usuarioId);
        if(usuario == null) return "redirect:/login";
        model.addAttribute("clientes", clienteService.getAll());
        return "/Terceros/Clientes.jsp";
    }

    @GetMapping("/clientes/add")
    public String addClienteForm(@ModelAttribute("cliente") Cliente _cliente, HttpSession session) {
        Long usuarioId = (Long) session.getAttribute("usuarioId");
        if(usuarioId == null) return "redirect:/login";
        Usuario usuario = usuarioService.getById(usuarioId);
        if(usuario == null) return "redirect:/login";
        if(usuario.getRol() != Usuario.Rol.ADMIN) return "redirect:/";
        return "/Terceros/ClientesFormulario.jsp";
    }

    @PostMapping("/clientes")
    public String addCliente(@Valid @ModelAttribute("cliente") Cliente cliente, BindingResult result, HttpSession session) {
        Long usuarioId = (Long) session.getAttribute("usuarioId");
        if(usuarioId == null) return "redirect:/login";
        Usuario usuario = usuarioService.getById(usuarioId);
        if(usuario == null) return "redirect:/login";
        if(usuario.getRol() != Usuario.Rol.ADMIN) return "redirect:/";
        if(result.hasErrors()) return "/Terceros/ClientesFormulario.jsp";
        clienteService.save(cliente);
        return "redirect:/clientes";
    }

}
