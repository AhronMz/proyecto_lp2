package com.primewash.proyecto_lp2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.primewash.proyecto_lp2.model.Cliente;
import com.primewash.proyecto_lp2.service.CatalogoService;
import com.primewash.proyecto_lp2.service.ClienteService;
import com.primewash.proyecto_lp2.util.Alert;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
@RequestMapping("/menu/cliente")
public class ClienteController {

	private final ClienteService clienteService;
	private final CatalogoService catalogoService;

	@GetMapping("/listado")
	public String listado(Model model) {
		model.addAttribute("lstCliente",clienteService.getClieAll());
		return "cliente/listado";
	}
	
	@GetMapping("nuevo")
	public String nuevo(Model model) {
		model.addAttribute("lstDistrito",catalogoService.getDistAll());
		model.addAttribute("lstSexo", catalogoService.getSexoAll());
		model.addAttribute("cliente", new Cliente());
		return "cliente/registro";
	}

	@PostMapping("registrar")
	public String registrar(@ModelAttribute Cliente c, RedirectAttributes flash) {
		var response = clienteService.create(c);
		var alert = Alert.sweetToast(response.mensaje(),"success",5000);
		
		flash.addFlashAttribute("alert",alert);
		return "redirect:/menu/cliente/listado";
	}
}
