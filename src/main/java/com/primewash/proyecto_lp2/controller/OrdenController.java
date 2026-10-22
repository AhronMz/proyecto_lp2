package com.primewash.proyecto_lp2.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.primewash.proyecto_lp2.dto.ItemSeleccionado;
import com.primewash.proyecto_lp2.dto.OrdenFilter;
import com.primewash.proyecto_lp2.model.Cliente;
import com.primewash.proyecto_lp2.model.DetalleOrden;
import com.primewash.proyecto_lp2.model.Empleado;
import com.primewash.proyecto_lp2.model.Orden;
import com.primewash.proyecto_lp2.service.ClienteService;
import com.primewash.proyecto_lp2.service.EmpleadoService;
import com.primewash.proyecto_lp2.service.OrdenService;
import com.primewash.proyecto_lp2.service.ProductoService;
import com.primewash.proyecto_lp2.service.ServicioService;
import com.primewash.proyecto_lp2.util.Alert;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/menu/orden")
@RequiredArgsConstructor
@SessionAttributes({ "seleccionados", "cliente" })
public class OrdenController {

    private final OrdenService ordenService;
    private final ClienteService clienteService;
    private final EmpleadoService empleadoService;
    private final ProductoService productoService;
    private final ServicioService servicioService;


    @ModelAttribute("seleccionados")
    public List<ItemSeleccionado> inicio() {
        return new ArrayList<ItemSeleccionado>();
    }

    @GetMapping("/listado")
    public String listado(@ModelAttribute OrdenFilter filter, Model model, HttpSession session) {
        model.addAttribute("lstOrdenes", ordenService.search(filter));
        model.addAttribute("filter", filter);
        return "orden/listado";
    }
    
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
    	model.addAttribute("clientes", clienteService.getAll());
        model.addAttribute("productos", productoService.getAll());
        model.addAttribute("servicios", servicioService.getAll());
        model.addAttribute("itemSeleccionado", new ItemSeleccionado());
        return "orden/registro";
    }

    @GetMapping("/validar-cliente")
    public String validarCliente(@RequestParam String accion, @RequestParam(required = false) String docid, 
                                 Model model, RedirectAttributes flash, HttpSession session) {

        if ("limpiar".equals(accion)) {
            model.addAttribute("cliente", null);
            session.removeAttribute("cliente");
            return "redirect:/menu/orden/nuevo";
        }

        var cliente = clienteService.getOneByDocid(docid);

        if (cliente == null) {
            flash.addFlashAttribute("alert", Alert.sweetAlertInfo("Cliente no encontrado con el documento ingresado"));
            return "redirect:/menu/orden/nuevo";
        }

        model.addAttribute("cliente", cliente);
        return "redirect:/menu/orden/nuevo";
    }
    
    
    @PostMapping("/anular/{id}")
    public String anular(@PathVariable Integer id, RedirectAttributes flash) {
        var response = ordenService.anular(id);
        var alert = Alert.sweetToast(response.mensaje(), response.success() ? "success" : "error", 5000);

        flash.addFlashAttribute("alert", alert);
        return "redirect:/menu/orden/listado";
    }
    
    @PostMapping("/agregar")
    public String agregar(@ModelAttribute ItemSeleccionado itemSeleccionado,
            @ModelAttribute("seleccionados") List<ItemSeleccionado> seleccionados, RedirectAttributes flash) {

        boolean existe = seleccionados.stream().anyMatch(item -> {
            if (itemSeleccionado.getIdProducto() != null) {
                return itemSeleccionado.getIdProducto().equals(item.getIdProducto());
            }
            if (itemSeleccionado.getIdServicio() != null) {
                return itemSeleccionado.getIdServicio().equals(item.getIdServicio());
            }
            return false;
        });

        if (existe) {
            flash.addFlashAttribute("alert", Alert.sweetAlertInfo("El ítem ya se encuentra en la lista"));
            return "redirect:/menu/orden/nuevo";
        }

        seleccionados.add(itemSeleccionado);
        return "redirect:/menu/orden/nuevo";
    }

    @PostMapping("/remover")
    public String remover(@RequestParam Integer index,
            @ModelAttribute("seleccionados") List<ItemSeleccionado> seleccionados) {

        if (index >= 0 && index < seleccionados.size()) {
            seleccionados.remove((int) index);
        }
        return "redirect:/menu/orden/nuevo";
    }

    @PostMapping("/registrar")
    public String registrar(@ModelAttribute("seleccionados") List<ItemSeleccionado> seleccionados,
            RedirectAttributes flash, HttpSession session, SessionStatus sessionStatus) {

        Integer idEmpleado = (Integer) session.getAttribute("ID_EMPLEADO");
        if (idEmpleado == null) {
            flash.addFlashAttribute("alert", Alert.sweetAlertInfo("La sesión ha caducado, inicie sesión nuevamente"));
            return "redirect:/";
        }

        Cliente cliente = (Cliente) session.getAttribute("cliente");
        if (cliente == null) {
            flash.addFlashAttribute("alert", Alert.sweetAlertInfo("Busque y seleccione un cliente primero"));
            return "redirect:/menu/orden/nuevo";
        }

        if (seleccionados.isEmpty()) {
            flash.addFlashAttribute("alert", Alert.sweetAlertInfo("Agregue al menos un ítem a la orden"));
            return "redirect:/menu/orden/nuevo";
        }

        Empleado empleado = empleadoService.getOne(idEmpleado);

        Orden orden = new Orden();
        orden.setNumBoleta(ordenService.generarNumeroBoleta());
        orden.setFecOrden(LocalDateTime.now());
        orden.setEstado("PAGADO");
        orden.setCliente(cliente);
        orden.setEmpleado(empleado);

        for (ItemSeleccionado item : seleccionados) {
            DetalleOrden det = new DetalleOrden();
            det.setCantidad(item.getCantidad());
            det.setPreUni(item.getPrecio());

            if (item.getIdProducto() != null) {
                det.setProducto(productoService.getOne(item.getIdProducto()));
            }
            if (item.getIdServicio() != null) {
                det.setServicio(servicioService.getOne(item.getIdServicio()));
            }

            orden.addDetalle(det);
        }

        var total = orden.getDetalles().stream()
                .map(d -> d.getPreUni().multiply(java.math.BigDecimal.valueOf(d.getCantidad())))
                .reduce(java.math.BigDecimal.ZERO, java.math.BigDecimal::add);
        orden.setTotal(total);

        var response = ordenService.create(orden);

        if (response.success()) {
            sessionStatus.setComplete();
        }

        flash.addFlashAttribute("alert", Alert.sweetToast(response.mensaje(), "success", 5000));
        return "redirect:/menu/orden/listado";
    }
}