package com.primewash.proyecto_lp2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {
	
	@GetMapping("/")
    public String root( ) {
		 return "redirect:/home/dashboard";
    }

}
