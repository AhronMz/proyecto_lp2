package com.primewash.proyecto_lp2.controller;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.primewash.proyecto_lp2.service.ReporteService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

@RestController
@RequestMapping("/reporte")
@RequiredArgsConstructor
public class ReporteController {
    
    private final ReporteService reporteService;

    // Cambiamos {id} por {codOrden} para seguir el estilo del profe
    @GetMapping("/orden/{codOrden}")
    public void imprimirOrden(@PathVariable Integer codOrden, HttpServletResponse response) throws Exception {
        
        // Ruta del reporte
        String reportPath = "/reportes/orden/orden.jrxml";
        
        // Parámetros
        Map<String, Object> params = new HashMap<>();
        params.put("pNumOrden", codOrden); 
        
        // Get JasperPrint
        JasperPrint jasperPrint = reporteService.getJasperPrint(params, reportPath);
        
        // Configuración de respuesta HTTP
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", String.format("inline; filename=boleta-orden-%d.pdf", codOrden));
        
        // Exportar a PDF
        OutputStream outputStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
        outputStream.flush();
        outputStream.close();
    }
}