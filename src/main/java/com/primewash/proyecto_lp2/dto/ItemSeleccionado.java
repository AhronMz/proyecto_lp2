package com.primewash.proyecto_lp2.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemSeleccionado {
    private Integer idServicio; 
    private Integer idProducto;
    private String nombre;
    private BigDecimal precio;
    private Integer cantidad=1;
}