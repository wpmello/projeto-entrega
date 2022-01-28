package com.algaworks.algalog.api.assembler;


import java.util.List;
import java.util.stream.Collectors;

import com.algaworks.algalog.api.model.OcorrenciaDTO;
import com.algaworks.algalog.domain.model.Ocorrencia;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class OcorrenciaAssembler {

	private ModelMapper modelMapper;
	
	public OcorrenciaDTO toDTO(Ocorrencia ocorrencia) {
		return modelMapper.map(ocorrencia, OcorrenciaDTO.class);
	}
	
	public List<OcorrenciaDTO> toCollectionDTO(List<Ocorrencia> ocorrencias) {
		return ocorrencias.stream()
				.map(this::toDTO)
				.collect(Collectors.toList());
	}
}
