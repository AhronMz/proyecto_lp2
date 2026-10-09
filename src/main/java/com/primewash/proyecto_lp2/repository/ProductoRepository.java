package com.primewash.proyecto_lp2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.primewash.proyecto_lp2.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer>{
	
}
