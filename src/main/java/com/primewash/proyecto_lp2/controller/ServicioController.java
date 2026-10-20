package com.primewash.proyecto_lp2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.primewash.proyecto_lp2.dto.ServicioFilter;
import com.primewash.proyecto_lp2.model.Servicio;
import com.primewash.proyecto_lp2.service.ServicioService;
import com.primewash.proyecto_lp2.util.Alert;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/menu/servicio")
public class ServicioController {

    private final ServicioService servicioService;

    @GetMapping("/listado")
    public String listado(@ModelAttribute ServicioFilter filter, Model model) {
        model.addAttribute("lstServicio", servicioService.search(filter));
        model.addAttribute("filter", filter);
        return "servicio/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("servicio", new Servicio());
        return "servicio/registro";
    }

    @PostMapping("/registrar")
    public String registrar(@ModelAttribute Servicio s, RedirectAttributes flash) {
        var response = servicioService.create(s);
        var alert = Alert.sweetToast(response.mensaje(), response.success() ? "success" : "error", 5000);
        flash.addFlashAttribute("alert", alert);
        return "redirect:/menu/servicio/listado";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        model.addAttribute("servicio", servicioService.getOne(id));
        return "servicio/editar";
    }

    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute Servicio s, RedirectAttributes flash) {
        var response = servicioService.update(s);
        var alert = Alert.sweetToast(response.mensaje(), response.success() ? "success" : "error", 5000);
        flash.addFlashAttribute("alert", alert);
        return "redirect:/menu/servicio/listado";
    }

    @GetMapping("/cambiar-estado/{id}")
    public String cambiarEstado(@PathVariable Integer id, RedirectAttributes flash) {
        var response = servicioService.toggleEstado(id);
        var alert = Alert.sweetToast(response.mensaje(), response.success() ? "success" : "error", 5000);
        flash.addFlashAttribute("alert", alert);
        return "redirect:/menu/servicio/listado";
    }
}