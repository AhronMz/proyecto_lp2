package com.primewash.proyecto_lp2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.primewash.proyecto_lp2.model.Distrito;
import com.primewash.proyecto_lp2.model.Sexo;
import com.primewash.proyecto_lp2.repository.DistritoRepository;
import com.primewash.proyecto_lp2.repository.SexoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CatalogoService {
	
	private final DistritoRepository distritoRepository;
	private final SexoRepository sexoRepository;
	
	
	public List<Distrito>getDistAll(){
		return distritoRepository.findAll();
	}
	

	public List<Sexo> getSexoAll(){
		return sexoRepository.findAll();
	}
	

}

