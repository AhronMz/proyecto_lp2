package com.primewash.proyecto_lp2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.primewash.proyecto_lp2.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
	List<Cliente> findAllByOrderByCodClienteDesc();
	
	
	@Query("""
			select c from Cliente c
			where
				c.distrito.codDistrito = :codDistrito or :codDistrito is null
			order by
				c.codCliente desc
			""")
	List<Cliente> findByFilters(@Param("codDistrito") Integer codDistrito);
	
	
}
