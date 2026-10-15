package com.primewash.proyecto_lp2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.primewash.proyecto_lp2.model.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer>{
	
	List<Empleado> findAllByOrderByCodEmpleadoDesc();
	
	@Query("""
			select e from Empleado e
			where
				(e.rol.codRol = :codRol or :codRol is null)
				and
				(e.distrito.codDistrito = :codDistrito or :codDistrito is null)
				and
				(e.estado = :estado or :estado is null)
			order by
				e.codEmpleado desc
			""")
	List<Empleado> findByFilters(@Param("codRol") Integer codRol,
								@Param("codDistrito") Integer codDistrito,
								@Param("estado") Boolean estado );
	
	
}
