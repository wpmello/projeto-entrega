package com.algaworks.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.service.CatalagoClienteService;
import com.algaworks.algalog.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;

	private CatalagoClienteService catalagoClienteService;
	
	@GetMapping
	public List<Cliente>list() {
		return clienteRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscar (@PathVariable Long id) {
		return clienteRepository.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
		return catalagoClienteService.salvar(cliente);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long id, @RequestBody Cliente cliente) {
		if (!clienteRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		cliente.setId(id);
		cliente = catalagoClienteService.salvar(cliente);
		
		return ResponseEntity.ok(cliente);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		if (!clienteRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		catalagoClienteService.excluir(id);
		
		return ResponseEntity.noContent().build();
	}
}
