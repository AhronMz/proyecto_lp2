package com.primewash.proyecto_lp2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.primewash.proyecto_lp2.dto.ResultadoResponse;
import com.primewash.proyecto_lp2.model.Cliente;
import com.primewash.proyecto_lp2.repository.ClienteRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService {

	private final ClienteRepository clienteRepository;

	public List<Cliente> getClieAll() {
		return clienteRepository.findAll();
	}
	@Transactional
	public ResultadoResponse create(Cliente c) {
		try {
			var registrado = clienteRepository.save(c);
			var mensaje = String.format("Cliente con Id %d registrado", registrado.getCodCliente());
			return new ResultadoResponse(true, mensaje);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultadoResponse(false, "Hubo un error en la transacción");
		}
	}
	
	public Cliente getOne(Integer id) {
		return clienteRepository.findById(id).orElse(null);
	}
	
	@Transactional
	public ResultadoResponse update(Cliente c) {
		try {
			var registrado = clienteRepository.save(c);
			var mensaje = String.format("Cliente con Id %d actualizado", registrado.getCodCliente());
			return new ResultadoResponse(true, mensaje);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultadoResponse(false, "Hubo un error en la transacción");
		}
	}
}