package com.primewash.proyecto_lp2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {

	@GetMapping("/menu")
	public String mostrarMenu() {
		return "menu/menuPrincipal";
	}
}
