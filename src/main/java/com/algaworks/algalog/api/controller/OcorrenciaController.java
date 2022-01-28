package com.algaworks.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.algaworks.algalog.api.assembler.OcorrenciaAssembler;
import com.algaworks.algalog.api.model.OcorrenciaDTO;
import com.algaworks.algalog.api.model.input.OcorrenciaInput;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.model.Ocorrencia;
import com.algaworks.algalog.domain.service.BuscarEntregaService;
import com.algaworks.algalog.domain.service.RegistroOcorrenciaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas/{entregaId}/ocorrencias")
public class OcorrenciaController {
	
	private BuscarEntregaService buscarEntregaService;
	private RegistroOcorrenciaService ocorrenciaService;
	private OcorrenciaAssembler ocorrenciaAssembler;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OcorrenciaDTO registrar(@PathVariable Long entregaId, @Valid @RequestBody OcorrenciaInput ocorrenciaInput) {
		Ocorrencia ocorrencia = ocorrenciaService.registrar(entregaId, ocorrenciaInput.getDescricao());
		
		return ocorrenciaAssembler.toDTO(ocorrencia);
	}
	@GetMapping
	public List<OcorrenciaDTO> listar(@PathVariable long entregaId) {
		Entrega entrega = buscarEntregaService.buscar(entregaId);
		
		return ocorrenciaAssembler.toCollectionDTO(entrega.getOcorrencias()) ;
	}

}