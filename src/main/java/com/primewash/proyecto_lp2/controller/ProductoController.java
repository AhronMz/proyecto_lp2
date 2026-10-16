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

import com.primewash.proyecto_lp2.dto.ProductoFilter;
import com.primewash.proyecto_lp2.model.Producto;
import com.primewash.proyecto_lp2.service.CatalogoService;
import com.primewash.proyecto_lp2.service.ProductoService;
import com.primewash.proyecto_lp2.util.Alert;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/menu/producto")
public class ProductoController {

	private final ProductoService productoService;
	private final CatalogoService catalogoService;

	@GetMapping("/listado")
	public String listado(@ModelAttribute ProductoFilter filter, Model model) {
		model.addAttribute("lstProducto", productoService.search(filter));
		model.addAttribute("lstCategoria", catalogoService.searchCategoriaProducto());
		model.addAttribute("filter", filter);	
		return "producto/listado";
	}
	
	@GetMapping("/nuevo")
	public String nuevo(Model model) {
		model.addAttribute("lstCategoria", catalogoService.getCategoriasAll());
		model.addAttribute("fecIngreso", LocalDate.now());
		model.addAttribute("producto", new Producto());
		return "producto/registro";
	}

	@PostMapping("/registrar")
	public String registrar(@ModelAttribute Producto p, RedirectAttributes flash) {
		var response = productoService.create(p);
		var alert = Alert.sweetToast(response.mensaje(),"success",5000);
		flash.addFlashAttribute("alert",alert);
		return "redirect:/menu/producto/listado";
	}
	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Integer id, Model model) {
		model.addAttribute("lstCategoria", catalogoService.getCategoriasAll());
		model.addAttribute("producto", productoService.getOne(id));
		return "producto/editar";
	}
	
	@PostMapping("/actualizar")
	public String guardar(@ModelAttribute Producto p, RedirectAttributes flash) {
		var response = productoService.update(p);
		var alert = Alert.sweetToast(response.mensaje(),response.success() ? "success" : "error", 5000);
		flash.addFlashAttribute("alert",alert);
		return "redirect:/menu/producto/listado";
	}
	
	@GetMapping("/cambiar-estado/{id}")
	public String cambiarEstado(@PathVariable Integer id, RedirectAttributes flash) {
		var response = productoService.toggleEstado(id);
		var alert = Alert.sweetToast(response.mensaje(), response.success() ? "success" : "error", 5000);

		flash.addFlashAttribute("alert", alert);
		return "redirect:/menu/producto/listado";
	}

}