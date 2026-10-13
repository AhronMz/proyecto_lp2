package com.primewash.proyecto_lp2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.primewash.proyecto_lp2.model.Rol;

public interface RolRepository extends JpaRepository<Rol, Integer>{
	
	@Query("""
			select distinct e.rol from Empleado e
			order by e.rol.nomRol asc
			""")
	List<Rol> findRolConEmpleado();
}
