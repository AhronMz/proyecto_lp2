package com.primewash.proyecto_lp2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.primewash.proyecto_lp2.dto.ProductoFilter;
import com.primewash.proyecto_lp2.dto.ResultadoResponse;
import com.primewash.proyecto_lp2.model.Producto;
import com.primewash.proyecto_lp2.repository.ProductoRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductoService {
	
	private final ProductoRepository productoRepository;

	public List<Producto> getAll() {
		return productoRepository.findAllByOrderByCodProductoDesc();
	}
	

	public List<Producto> search(ProductoFilter filter){
		return productoRepository.findByFilters(filter.codCategoria(), filter.estado());
	}
	
	@Transactional
	public ResultadoResponse create(Producto p) {
		try {
			var registrado = productoRepository.save(p);
			var mensaje = String.format("Producto con Id %d registrado", registrado.getCodProducto());
			return new ResultadoResponse(true, mensaje);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultadoResponse(false, "Hubo un error en la transacción");
		}
	}
	
	public Producto getOne(Integer id) {
		return productoRepository.findById(id).orElse(null);
	}
	
	@Transactional
	public ResultadoResponse update(Producto p) {
		try {
			var registrado = productoRepository.save(p);
			var mensaje = String.format("Producto con Id %d actualizado", registrado.getCodProducto());
			return new ResultadoResponse(true, mensaje);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultadoResponse(false, "Hubo un error en la transacción");
		}
	}
	
	@Transactional
	public ResultadoResponse toggleEstado(Integer id) {
		try {
			var producto = getOne(id);
			if (producto == null) {
				return new ResultadoResponse(false, "El empleado no existe");
			}
			

			boolean nuevoEstado = !Boolean.TRUE.equals(producto.getEstado());
			producto.setEstado(nuevoEstado);
			productoRepository.save(producto);

			String estadoTexto = nuevoEstado ? "activado" : "deshabilitado";
			var mensaje = String.format("Producto con Id %d %s correctamente", id, estadoTexto);
			return new ResultadoResponse(true, mensaje);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResultadoResponse(false, "Hubo un error al cambiar el estado del producto");
		}
	}

}