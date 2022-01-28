package com.algaworks.algalog.domain.service;

import java.time.OffsetDateTime;

import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.model.StatusEntrega;
import com.algaworks.algalog.repository.EntregaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SolicitacaoEntregaService {

	private CatalagoClienteService catalagoClienteService;
	
	private EntregaRepository entregaRepository;
	
	@Transactional
	public Entrega solicitar(Entrega entrega) {
	Cliente cliente = catalagoClienteService.buscar(entrega.getCliente().getId());
		
		entrega.setCliente(cliente);
		entrega.setStatus(StatusEntrega.PENDENTE);
		entrega.setDataPedido(OffsetDateTime.now());
		
		return entregaRepository.save(entrega);
	}
}
