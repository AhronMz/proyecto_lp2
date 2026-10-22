package com.primewash.proyecto_lp2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.primewash.proyecto_lp2.dto.AutenticacionFilter;
import com.primewash.proyecto_lp2.service.AutenticacionService;
import com.primewash.proyecto_lp2.util.Alert;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final AutenticacionService autenticacionService;

    @GetMapping("/")
    public String root(Model model) {
        model.addAttribute("filter", new AutenticacionFilter(null, null));
        return "login";
    }

    @GetMapping("iniciar-sesion")
    public String iniciarSesion(@ModelAttribute AutenticacionFilter filter, RedirectAttributes flash, Model model, HttpSession session) {

        var usuario = autenticacionService.autentic(filter);

        if (usuario == null) {
            var alert = Alert.sweetAlertError("Cuenta y/o clave inválidos");
            model.addAttribute("alert", alert);
            model.addAttribute("filter", new AutenticacionFilter(null, null));
            return "login";
        }

        session.setAttribute("fullName", usuario.getNombreCompleto());
        session.setAttribute("ID_TIPO", usuario.getRol().getCodRol());
        
        session.setAttribute("ID_EMPLEADO", usuario.getCodEmpleado());

        String alert = Alert.sweetImageUrl("Bienvenido a PrimeWash", usuario.getNombreCompleto(), "/imagenes/bienvenida_carwash.gif");
        flash.addFlashAttribute("alert", alert);
        return "redirect:/home/dashboard";
    }

    @GetMapping("cerrar-sesion")
    public String cerrarSesion(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}