package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.repository.EntregaRepository;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BuscarEntregaService {

	private EntregaRepository entregaRepository;
	
	public Entrega buscar (Long id) {
		return entregaRepository.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada."));
	}
}
