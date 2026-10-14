package com.primewash.proyecto_lp2.service;

import org.springframework.stereotype.Service;

import com.primewash.proyecto_lp2.dto.AutenticacionFilter;
import com.primewash.proyecto_lp2.model.Empleado;
import com.primewash.proyecto_lp2.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AutenticacionService {
	private final UsuarioRepository usuarioRepository;
	
	public Empleado autentic(AutenticacionFilter filter) {
		return usuarioRepository.findByUsuEmpleadoAndClaEmpleado(filter.usuario(), filter.clave());
	}
}
