package com.primewash.proyecto_lp2.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.primewash.proyecto_lp2.model.Orden;

public interface OrdenRepository extends JpaRepository<Orden, Integer>{
	Optional<Orden> findTopByOrderByCodOrdenDesc();
	
	@Query("""
            select o from Orden o
            where
                (o.empleado.codEmpleado = :codEmpleado or :codEmpleado is null)
            order by
                o.codOrden desc
            """)
    List<Orden> findByFilters(@Param("codEmpleado") Integer codEmpleado);
}
