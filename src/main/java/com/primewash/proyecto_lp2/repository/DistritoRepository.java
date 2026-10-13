package com.primewash.proyecto_lp2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.primewash.proyecto_lp2.model.Distrito;

public interface DistritoRepository extends JpaRepository<Distrito, Integer>{
	
	@Query("""
	        select distinct c.distrito 
	        from Cliente c 
	        order by c.distrito.nomDistrito asc
	    """)	
	List<Distrito> findDistritoConCliente();
	
	@Query("""
			select distinct e.distrito from Empleado e
			order by e.distrito.nomDistrito asc
			""")
	List<Distrito> findDistritoConEmpleado();
}
