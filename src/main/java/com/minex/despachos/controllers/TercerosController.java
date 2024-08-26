package com.minex.despachos.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

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
import org.springframework.web.bind.annotation.PathVariable;


@Controller
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
        model.addAttribute("usuario", usuario);
        return "/Terceros/Camiones.jsp";
    }

    @GetMapping("/camiones/add")
    public String addCamionForm(@ModelAttribute("camion") Camion camion, HttpSession session, Model model) {
        Long usuarioId = (Long) session.getAttribute("usuarioId");
        if(usuarioId == null) return "redirect:/login";
        Usuario usuario = usuarioService.getById(usuarioId);
        if(usuario == null) return "redirect:/login";
        if(usuario.getRol() != Usuario.Rol.ADMIN) return "redirect:/";
        model.addAttribute("usuario", usuario);
        return "/Terceros/CamionesFormulario.jsp";
    }

    @PostMapping("/camiones/add")
    public String addCamion(@Valid @ModelAttribute("camion") Camion camion, BindingResult result, HttpSession session, Model model) {
        Long usuarioId = (Long) session.getAttribute("usuarioId");
        if(usuarioId == null) return "redirect:/login";
        Usuario usuario = usuarioService.getById(usuarioId);
        if(usuario == null) return "redirect:/login";
        // Checkear si la patente ya está registrada
        if(camionService.existsByPatente(camion.getPatente())) result.rejectValue("patente", "error.camion", "La patente ya está registrada");
        if(result.hasErrors()){
            return "/Terceros/CamionesFormulario.jsp";
        }
        camionService.save(camion);
        return "redirect:/camiones";
    }

    @PostMapping("/camiones/{id}/cambiar-estado")
    public String cambiarEstadoCamion(@PathVariable String id, HttpSession session) {
        Long usuarioId = (Long) session.getAttribute("usuarioId");
        if(usuarioId == null) return "redirect:/login";
        Usuario usuario = usuarioService.getById(usuarioId);
        if(usuario == null) return "redirect:/login";
        if(usuario.getRol() != Usuario.Rol.ADMIN) return "redirect:/";
        Camion camion = camionService.getById(Long.parseLong(id));
        if(camion == null) return "redirect:/camiones";
        camion.setActivo(!camion.isActivo());
        camionService.save(camion);
        return "redirect:/camiones";
    }

    @GetMapping("/camiones/{id}/edit")
    public String editCamionForm(@PathVariable String id, HttpSession session, Model model) {
        Long usuarioId = (Long) session.getAttribute("usuarioId");
        if(usuarioId == null) return "redirect:/login";
        Usuario usuario = usuarioService.getById(usuarioId);
        if(usuario == null) return "redirect:/login";
        if(usuario.getRol() != Usuario.Rol.ADMIN) return "redirect:/";
        Camion camion = camionService.getById(Long.parseLong(id));
        if(camion == null) return "redirect:/camiones";
        model.addAttribute("usuario", usuario);
        model.addAttribute("camion", camion);
        return "/Terceros/CamionesEdit.jsp";
    }

    @PostMapping("/camiones/{id}/edit")
    public String editCamion(@Valid @ModelAttribute("camion") Camion camion, BindingResult result, @PathVariable Long id, HttpSession session) {
        Long usuarioId = (Long) session.getAttribute("usuarioId");
        if(usuarioId == null) return "redirect:/login";
        Usuario usuario = usuarioService.getById(usuarioId);
        if(usuario == null) return "redirect:/login";
        if(result.hasErrors()) return "/Terceros/CamionesEdit.jsp";
        Camion _camion = camionService.getById(id);
        if(_camion == null) return "redirect:/camiones";
        _camion.setPatente(camion.getPatente());
        _camion.setModelo(camion.getModelo());
        _camion.setColor(camion.getColor());
        camionService.save(_camion);
        return "redirect:/camiones";
    }

    // TODO: delete endpoints

    // Chofer endpoints, abiertos para todos
    @GetMapping("/choferes")
    public String showChoferes(HttpSession session, Model model){
        Long usuarioId = (Long) session.getAttribute("usuarioId");
        if(usuarioId == null) return "redirect:/login";
        Usuario usuario = usuarioService.getById(usuarioId);
        if(usuario == null) return "redirect:/login";
        model.addAttribute("usuario", usuario);
        model.addAttribute("choferes", choferService.getAll());
        return "/Terceros/Choferes.jsp";
    }

    @GetMapping("/choferes/add")
    public String addChoferForm(@ModelAttribute("chofer") Chofer _chofer, HttpSession session, Model model) {
        Long usuarioId = (Long) session.getAttribute("usuarioId");
        if(usuarioId == null) return "redirect:/login";
        Usuario usuario = usuarioService.getById(usuarioId);
        if(usuario == null) return "redirect:/login";
        if(usuario.getRol() != Usuario.Rol.ADMIN) return "redirect:/";
        model.addAttribute("usuario", usuario);
        return "/Terceros/ChoferesFormulario.jsp";
    }

    @PostMapping("/choferes/add")
    public String addChofer(@Valid @ModelAttribute("chofer") Chofer chofer, BindingResult result, HttpSession session) {
        Long usuarioId = (Long) session.getAttribute("usuarioId");
        if(usuarioId == null) return "redirect:/login";
        Usuario usuario = usuarioService.getById(usuarioId);
        if(usuario == null) return "redirect:/login";
        // Checkear si el rut ya está registrado
        if(choferService.existsByRut(chofer.getRut())) {
            result.rejectValue("rut", "error.chofer", "El rut ya está registrado");
        }
        if(result.hasErrors()) return "/Terceros/ChoferesFormulario.jsp";
        choferService.save(chofer);
        return "redirect:/choferes";
    }

    @PostMapping("/choferes/{id}/cambiar-estado")
    public String cambiarEstadoChofer(@PathVariable String id, HttpSession session) {
        Long usuarioId = (Long) session.getAttribute("usuarioId");
        if(usuarioId == null) return "redirect:/login";
        Usuario usuario = usuarioService.getById(usuarioId);
        if(usuario == null) return "redirect:/login";
        if(usuario.getRol() != Usuario.Rol.ADMIN) return "redirect:/";
        Chofer chofer = choferService.getById(Long.parseLong(id));
        if(chofer == null) return "redirect:/choferes";
        chofer.setActivo(!chofer.getActivo());
        choferService.save(chofer);
        return "redirect:/choferes";
    }

    @GetMapping("/choferes/{id}/edit")
    public String editChoferForm(@ModelAttribute("chofer") Chofer chofer, @PathVariable String id, HttpSession session, Model model) {
        Long usuarioId = (Long) session.getAttribute("usuarioId");
        if(usuarioId == null) return "redirect:/login";
        Usuario usuario = usuarioService.getById(usuarioId);
        if(usuario == null) return "redirect:/login";
        if(usuario.getRol() != Usuario.Rol.ADMIN) return "redirect:/";
        Chofer _chofer = choferService.getById(Long.parseLong(id));
        if(chofer == null) return "redirect:/choferes";
        model.addAttribute("usuario", usuario);
        model.addAttribute("chofer", _chofer);
        return "/Terceros/ChoferesEdit.jsp";
    }

    @PostMapping("/choferes/{id}/edit")
    public String editChofer(@Valid @ModelAttribute Chofer chofer, BindingResult result, @PathVariable Long id, HttpSession session, Model model) {
        Long usuarioId = (Long) session.getAttribute("usuarioId");
        if(usuarioId == null) return "redirect:/login";
        Usuario usuario = usuarioService.getById(usuarioId);
        if(usuario == null) return "redirect:/login";
        Chofer _chofer = choferService.getById(id);
        if(_chofer == null) return "redirect:/choferes";
        System.out.println(result.getAllErrors());
        if(result.hasErrors()){
            model.addAttribute("usuario", usuario);
            model.addAttribute("chofer", chofer);
            return "/Terceros/ChoferesEdit.jsp";
        }
        _chofer.setNombre(chofer.getNombre());
        choferService.save(_chofer);
        return "redirect:/choferes";
    }
    
    // TODO: delete endpoints

    // Productor endpoints, post solo admins
    @GetMapping("/productores")
    public String showProductores(HttpSession session, Model model){
        Long usuarioId = (Long) session.getAttribute("usuarioId");
        if(usuarioId == null) return "redirect:/login";
        Usuario usuario = usuarioService.getById(usuarioId);
        if(usuario == null) return "redirect:/login";
        model.addAttribute("usuario", usuario);
        model.addAttribute("productores", productorService.getAll());
        return "/Terceros/Productores.jsp";
    }

    @GetMapping("/productores/add")
    public String addProductorForm(@ModelAttribute("productor") Productor _productor, HttpSession session, Model model) {
        Long usuarioId = (Long) session.getAttribute("usuarioId");
        if(usuarioId == null) return "redirect:/login";
        Usuario usuario = usuarioService.getById(usuarioId);
        if(usuario == null) return "redirect:/login";
        if(usuario.getRol() != Usuario.Rol.ADMIN) return "redirect:/";
        model.addAttribute("usuario", usuario);
        return "/Terceros/ProductoresFormulario.jsp";
    }

    @PostMapping("/productores/add")
    public String addProductor(@Valid @ModelAttribute("productor") Productor productor, BindingResult result, HttpSession session) {
        Long usuarioId = (Long) session.getAttribute("usuarioId");
        if(usuarioId == null) return "redirect:/login";
        Usuario usuario = usuarioService.getById(usuarioId);
        if(usuario == null) return "redirect:/login";
        if(usuario.getRol() != Usuario.Rol.ADMIN) return "redirect:/";
        // Checkear si el rut ya está registrado
        if(productorService.existsByRut(productor.getRut())) result.rejectValue("rut", "error.productor", "El rut ya está registrado");
        if(result.hasErrors()) return "/Terceros/ProductoresFormulario.jsp";
        productorService.save(productor);
        return "redirect:/productores";
    }

    @GetMapping("/productores/{id}/edit")
    public String editProductorForm(@PathVariable("id") Long id, @ModelAttribute("productor") Productor productor, HttpSession session, Model model) {
        Long usuarioId = (Long) session.getAttribute("usuarioId");
        if(usuarioId == null) return "redirect:/login";
        Usuario usuario = usuarioService.getById(usuarioId);
        if(usuario == null) return "redirect:/login";
        if(usuario.getRol() != Usuario.Rol.ADMIN) return "redirect:/";
        Productor _productor = productorService.getById(id);
        if(_productor == null) return "redirect:/productores";
        model.addAttribute("usuario", usuario);
        model.addAttribute("productor", _productor);
        return "/Terceros/ProductoresEdit.jsp";
    }

    @PostMapping("/productores/{id}/edit")
    public String editProductor(@PathVariable("id") Long id, @Valid @ModelAttribute Productor productor, BindingResult result, HttpSession session, Model model) {
        Long usuarioId = (Long) session.getAttribute("usuarioId");
        System.out.println(productor.getId());
        if(usuarioId == null) return "redirect:/login";
        Usuario usuario = usuarioService.getById(usuarioId);
        if(usuario == null) return "redirect:/login";
        Productor _productor = productorService.getById(id);
        if(_productor == null) return "redirect:/productores";
        if(result.hasErrors()){
            model.addAttribute("usuario", usuario);
            model.addAttribute("productor", productor);
            System.out.println(result.getAllErrors());
            return "/Terceros/ProductoresEdit.jsp";
        }
        _productor.setRut(productor.getRut());
        _productor.setRazonSocial(productor.getRazonSocial());
        _productor.setComunaActual(productor.getComunaActual());
        _productor.setCiudadActual(productor.getCiudadActual());
        _productor.setDireccionActual(productor.getDireccionActual());
        _productor.setEmail(productor.getEmail());
        _productor.setTelefono(productor.getTelefono());
        productorService.save(_productor);
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
        model.addAttribute("usuario", usuario);
        model.addAttribute("clientes", clienteService.getAll());
        return "/Terceros/Clientes.jsp";
    }

    @GetMapping("/clientes/add")
    public String addClienteForm(@ModelAttribute("cliente") Cliente _cliente, HttpSession session, Model model) {
        Long usuarioId = (Long) session.getAttribute("usuarioId");
        if(usuarioId == null) return "redirect:/login";
        Usuario usuario = usuarioService.getById(usuarioId);
        if(usuario == null) return "redirect:/login";
        if(usuario.getRol() != Usuario.Rol.ADMIN) return "redirect:/";
        model.addAttribute("usuario", usuario);
        return "/Terceros/ClientesFormulario.jsp";
    }

    @PostMapping("/clientes/add")
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
