package com.primewash.proyecto_lp2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.primewash.proyecto_lp2.dto.OrdenFilter;
import com.primewash.proyecto_lp2.dto.ResultadoResponse;
import com.primewash.proyecto_lp2.model.Orden;
import com.primewash.proyecto_lp2.repository.OrdenRepository;
import com.primewash.proyecto_lp2.repository.ProductoRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrdenService {

    private final OrdenRepository ordenRepository;
    private final ProductoRepository productoRepository;

    // Búsqueda con filtro por empleado
    public List<Orden> search(OrdenFilter filter) {
        if (filter == null) {
            return ordenRepository.findByFilters(null);
        }
        return ordenRepository.findByFilters(filter.codEmpleado());
    }
    
    
    // Listado general de órdenes
    public List<Orden> getAll() {
        return ordenRepository.findAll();
    }

    // Generador del correlativo de boleta (BOL-0001, BOL-0002, etc.)
    public String generarNumeroBoleta() {
        var ultimaOrden = ordenRepository.findTopByOrderByCodOrdenDesc();
        if (ultimaOrden.isPresent()) {
            int siguiente = ultimaOrden.get().getCodOrden() + 1;
            return String.format("BOL-%04d", siguiente);
        }
        return "BOL-0001";
    }

    @Transactional
    public ResultadoResponse create(Orden orden) {
        try {
            // Descuento de stock para los productos incluidos en el detalle
            for (var det : orden.getDetalles()) {
                if (det.getProducto() != null) {
                    var prod = det.getProducto();
                    prod.setCanProducto(prod.getCanProducto() - det.getCantidad());
                    productoRepository.save(prod);
                }
            }

            // Guardado en cascada de la Orden y sus DetalleOrden
            var registrada = ordenRepository.save(orden);
            var mensaje = String.format("Orden con Nro. %s registrada", registrada.getNumBoleta());

            return new ResultadoResponse(true, mensaje);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultadoResponse(false, "Hubo un error al registrar la orden");
        }
    }
    
    @Transactional
    public ResultadoResponse anular(Integer idOrden) {
        try {
            var optOrden = ordenRepository.findById(idOrden);
            if (optOrden.isEmpty()) {
                return new ResultadoResponse(false, "La orden no existe");
            }

            var orden = optOrden.get();

            if ("ANULADO".equals(orden.getEstado())) {
                return new ResultadoResponse(false, "La orden ya se encuentra anulada");
            }

            for (var det : orden.getDetalles()) {
                if (det.getProducto() != null) {
                    var prod = det.getProducto();
                    prod.setCanProducto(prod.getCanProducto() + det.getCantidad());
                    productoRepository.save(prod);
                }
            }

            orden.setEstado("ANULADO");
            ordenRepository.save(orden);

            var mensaje = String.format("Orden Nro. %s anulada exitosamente", orden.getNumBoleta());
            return new ResultadoResponse(true, mensaje);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResultadoResponse(false, "Hubo un error al anular la orden");
        }
    }
    

}