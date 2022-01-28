package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.domain.exception.NegocioException;
import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CatalagoClienteService {

	private ClienteRepository clienteRepository;

	
	public Cliente buscar(Long id) {
		return clienteRepository.findById(id)
				.orElseThrow(() -> new NegocioException("Cliente não encontrado"));
	}
	
	@Transactional
	public Cliente salvar(Cliente cliente) {
		boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
				.stream()
				.anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
		if (emailEmUso) {
			throw new NegocioException("Já existe um cliente cadastrado com esse email.");
		}
		
		return clienteRepository.save(cliente);
	}
	
	@Transactional
	public void excluir(Long id) {
		clienteRepository.deleteById(id);
	}
}
