package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.model.Ocorrencia;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegistroOcorrenciaService {

	private BuscarEntregaService buscarEntregaService;
	
	@Transactional
	public Ocorrencia registrar(Long id, String descricao) {
		Entrega entrega = buscarEntregaService.buscar(id);
		
		return entrega.adicionarOcorrencia(descricao);
	}
}
