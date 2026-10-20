package com.primewash.proyecto_lp2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.primewash.proyecto_lp2.model.Servicio;

public interface ServicioRepository extends JpaRepository<Servicio, Integer>{
	
	List<Servicio> findAllByOrderByCodserDesc();

    @Query("""
            select s from Servicio s
            where
                (:estado is null or s.estado = :estado)
            order by
                s.codser desc
            """)
    List<Servicio> findByFilters(@Param("estado") Boolean estado);
}
