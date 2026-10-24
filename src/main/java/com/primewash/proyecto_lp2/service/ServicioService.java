package com.primewash.proyecto_lp2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.primewash.proyecto_lp2.dto.ResultadoResponse;
import com.primewash.proyecto_lp2.dto.ServicioFilter;
import com.primewash.proyecto_lp2.model.Servicio;
import com.primewash.proyecto_lp2.repository.ServicioRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServicioService {
	
	private final ServicioRepository servicioRepository;
	
	public List<Servicio> getAll(){
		return servicioRepository.findAllByOrderByCodserDesc();
	}
	
	public List<Servicio> search(ServicioFilter filter) {	
        return servicioRepository.findByFilters(filter.estado());
    }
	
	@Transactional
    public ResultadoResponse create(Servicio s) {
        try {
            var registrado = servicioRepository.save(s);
            var mensaje = String.format("Servicio con Id %d registrado", registrado.getCodser());
            return new ResultadoResponse(true, mensaje);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultadoResponse(false, "Hubo un error en la transacción");
        }
    }
	
	public Servicio getOne(Integer id) {
        return servicioRepository.findById(id).orElse(null);
    }

    @Transactional
    public ResultadoResponse update(Servicio s) {
        try {
            var registrado = servicioRepository.save(s);
            var mensaje = String.format("Servicio con Id %d actualizado", registrado.getCodser());
            return new ResultadoResponse(true, mensaje);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultadoResponse(false, "Hubo un error en la transacción");
        }
    }
    
	@Transactional
	public ResultadoResponse toggleEstado(Integer id) {
		try {
			var servicio = servicioRepository.findById(id).orElse(null);
			
			if (servicio == null) {
				return new ResultadoResponse(false, "El servicio no existe");
			}
			

			servicio.setEstado(!servicio.getEstado());
			
			var mensaje = String.format("Servicio con Id %d %s", id, servicio.getEstado() ? "activado" : "deshabilitado");
			
			return new ResultadoResponse(true, mensaje);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultadoResponse(false, "Hubo un error al cambiar el estado del servicio");
		}
	}
}

