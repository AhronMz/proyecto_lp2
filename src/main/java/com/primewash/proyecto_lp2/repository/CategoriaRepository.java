package com.primewash.proyecto_lp2.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.primewash.proyecto_lp2.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{
	
	@Query("""
	        select distinct p.categoria 
	        from Producto p 
	        order by p.categoria.nomCategoria asc
	    """)	
	List<Categoria> findCategoriaConProducto();
}
