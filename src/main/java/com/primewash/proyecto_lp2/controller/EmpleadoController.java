package com.primewash.proyecto_lp2.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.primewash.proyecto_lp2.dto.EmpleadoFilter;
import com.primewash.proyecto_lp2.model.Empleado;
import com.primewash.proyecto_lp2.service.CatalogoService;
import com.primewash.proyecto_lp2.service.EmpleadoService;
import com.primewash.proyecto_lp2.util.Alert;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/menu/empleado")
public class EmpleadoController {

	private final EmpleadoService empleadoService;
	private final CatalogoService catalogoService;

	@GetMapping("/listado")
	public String listado(@ModelAttribute EmpleadoFilter filter ,Model model) {
		model.addAttribute("lstEmpleado", empleadoService.search(filter));
		model.addAttribute("lstRol", catalogoService.searchRolEmpleado());
		model.addAttribute("lstDistrito", catalogoService.searchDistritoEmpleado());
		model.addAttribute("filter", filter);
		return "empleado/listado";
	}

	@GetMapping("/nuevo")
	public String nuevo(Model model) {
		model.addAttribute("lstDistrito", catalogoService.getDistAll());
		model.addAttribute("lstSexo", catalogoService.getSexoAll());
		model.addAttribute("lstRol", catalogoService.getRolesAll());
		model.addAttribute("fecEmpleado", LocalDate.now());
		model.addAttribute("empleado", new Empleado());
		return "empleado/registro";
	}

	@PostMapping("/registrar")
	public String registrar(@ModelAttribute Empleado e, RedirectAttributes flash) {
		var response = empleadoService.create(e);
		var alert = Alert.sweetToast(response.mensaje(), response.success() ? "success" : "error", 5000);

		flash.addFlashAttribute("alert", alert);
		return "redirect:/menu/empleado/listado";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Integer id, Model model) {
		model.addAttribute("lstDistrito", catalogoService.getDistAll());
		model.addAttribute("lstSexo", catalogoService.getSexoAll());
		model.addAttribute("lstRol", catalogoService.getRolesAll());
		model.addAttribute("empleado", empleadoService.getOne(id));
		return "empleado/editar";
	}

	@PostMapping("/actualizar")
	public String actualizar(@ModelAttribute Empleado e, RedirectAttributes flash) {
		var response = empleadoService.update(e);
		var alert = Alert.sweetToast(response.mensaje(), response.success() ? "success" : "error", 5000);

		flash.addFlashAttribute("alert", alert);
		return "redirect:/menu/empleado/listado";
	}

	@GetMapping("/cambiar-estado/{id}")
	public String cambiarEstado(@PathVariable Integer id, RedirectAttributes flash) {
		var response = empleadoService.toggleEstado(id);
		var alert = Alert.sweetToast(response.mensaje(), response.success() ? "success" : "error", 5000);

		flash.addFlashAttribute("alert", alert);
		return "redirect:/menu/empleado/listado";
	}
}