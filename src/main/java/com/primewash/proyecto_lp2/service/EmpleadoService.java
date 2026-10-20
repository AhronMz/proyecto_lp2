package com.primewash.proyecto_lp2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.primewash.proyecto_lp2.dto.EmpleadoFilter;
import com.primewash.proyecto_lp2.dto.ResultadoResponse;
import com.primewash.proyecto_lp2.model.Empleado;
import com.primewash.proyecto_lp2.repository.EmpleadoRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmpleadoService {

	private final EmpleadoRepository empleadoRepository;

	public List<Empleado> getAll() {
		return empleadoRepository.findAllByOrderByCodEmpleadoDesc();
	}
	
	public List<Empleado> search(EmpleadoFilter filter) {

		return empleadoRepository.findByFilters(filter.codRol(),filter.codDistrito(),filter.estado());
	}
	
	@Transactional
	public ResultadoResponse create(Empleado e) {
		try {
			var registrado = empleadoRepository.save(e);
			var mensaje = String.format("Empleado con Id %d registrado", registrado.getCodEmpleado());
			return new ResultadoResponse(true, mensaje);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResultadoResponse(false, "Hubo un error en la transacción");
		}
	}

	public Empleado getOne(Integer id) {
		return empleadoRepository.findById(id).orElse(null);
	}

	@Transactional
	public ResultadoResponse update(Empleado e) {
		try {
			var registrado = empleadoRepository.save(e);
			var mensaje = String.format("Empleado con Id %d actualizado", registrado.getCodEmpleado());
			return new ResultadoResponse(true, mensaje);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResultadoResponse(false, "Hubo un error en la transacción");
		}
	}

	@Transactional
	public ResultadoResponse toggleEstado(Integer id) {
		try {
			var empleado = empleadoRepository.findById(id).orElse(null);
			
			if (empleado == null) {
				return new ResultadoResponse(false, "El empleado no existe");
			}
			
			// Inversión directa del estado sin variables intermedias
			empleado.setEstado(!empleado.getEstado());
			
			var mensaje = String.format("Empleado con Id %d %s", id, empleado.getEstado() ? "activado" : "deshabilitado");
			
			return new ResultadoResponse(true, mensaje);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultadoResponse(false, "Hubo un error al cambiar el estado del empleado");
		}
	}
}