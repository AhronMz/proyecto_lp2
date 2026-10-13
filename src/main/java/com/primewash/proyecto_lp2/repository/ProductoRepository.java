package com.primewash.proyecto_lp2.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.primewash.proyecto_lp2.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer>{
	
	List<Producto> findAllByOrderByCodProductoDesc();
	
	@Query("""
			select p from Producto p
			where
				p.categoria.codCategoria = :codCategoria or :codCategoria is null
			order by
				p.codProducto desc
			""")
	List<Producto> findByFilters(@Param("codCategoria") Integer codCategoria);
}