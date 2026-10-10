package com.primewash.proyecto_lp2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.primewash.proyecto_lp2.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
	List<Cliente> findAllByOrderByCodClienteDesc();
}
