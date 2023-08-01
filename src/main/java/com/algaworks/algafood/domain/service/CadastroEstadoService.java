package com.algaworks.algafood.domain.service;

import java.util.Optional;

import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {

	private static final String MSG_ESTADO_EM_USO = "Estado de código %d não pode ser removida, pois está em uso";

	@Autowired
	private EstadoRepository estadoRepository;

	public Estado salvar(Estado estado) {
		return estadoRepository.save(estado);
	}

	public void excluir(Long estadoId) {
		Optional<Estado> estado = estadoRepository.findById(estadoId);
		try {
			if (estado.isEmpty()) {
				throw new EstadoNaoEncontradoException(estadoId);
			}
			estadoRepository.deleteById(estadoId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_ESTADO_EM_USO, estadoId));
		}
	}

	public Estado buscarOuFalhar(Long cidadeId) {
		return estadoRepository.findById(cidadeId)
				.orElseThrow(() -> new EstadoNaoEncontradoException(cidadeId));
	}

}
