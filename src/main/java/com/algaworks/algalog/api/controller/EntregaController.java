package com.algaworks.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.algaworks.algalog.api.assembler.EntregaAssembler;
import com.algaworks.algalog.api.model.EntregaDTO;
import com.algaworks.algalog.api.model.input.EntregaInput;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.service.FinalizacaoEntregaService;
import com.algaworks.algalog.domain.service.SolicitacaoEntregaService;
import com.algaworks.algalog.repository.EntregaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {

	private SolicitacaoEntregaService solicitacaoEntregaService;
	private EntregaRepository entregaRepository;
	private EntregaAssembler entregaAssembler;
	private FinalizacaoEntregaService finalizacaoEntregaService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntregaDTO solicitar(@Valid @RequestBody EntregaInput entregaInput) {
		Entrega novaEntrega = entregaAssembler.toEntity(entregaInput);
		return entregaAssembler.toDTO(solicitacaoEntregaService.solicitar(novaEntrega));
	}
	
	@GetMapping
	public List<EntregaDTO> listar() {
		return entregaAssembler.toCollectDTO(entregaRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EntregaDTO> buscar(@PathVariable Long id) {
		return entregaRepository.findById(id)
				.map(entrega -> ResponseEntity.ok(entregaAssembler.toDTO(entrega)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/{entregaId}/finalizacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finalizar(@PathVariable Long entregaId) {
		finalizacaoEntregaService.finalizar(entregaId);
	}
}
