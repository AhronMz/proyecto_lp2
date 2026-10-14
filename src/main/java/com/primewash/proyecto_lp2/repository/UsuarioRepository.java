package com.primewash.proyecto_lp2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.primewash.proyecto_lp2.model.Empleado;

public interface UsuarioRepository extends JpaRepository<Empleado, Integer> {
	Empleado findByUsuEmpleadoAndClaEmpleado(String usuario, String clave);
}
