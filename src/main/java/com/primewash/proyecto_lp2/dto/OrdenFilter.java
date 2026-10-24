package com.primewash.proyecto_lp2.dto;

public record OrdenFilter(
    Integer codEmpleado
) {
    public OrdenFilter withCodEmpleado(Integer codEmpleado) {
        return new OrdenFilter(codEmpleado);
    }
    
    
}