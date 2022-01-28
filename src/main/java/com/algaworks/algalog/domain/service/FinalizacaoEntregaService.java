package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.repository.EntregaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FinalizacaoEntregaService {

	private EntregaRepository entregaRepository;
	private BuscarEntregaService buscarEntregaService;
	
	@Transactional
	public void finalizar(Long entregaId) {
		Entrega entrega = buscarEntregaService.buscar(entregaId);
		
		entrega.finalizar();
		entregaRepository.save(entrega);
	}
}
