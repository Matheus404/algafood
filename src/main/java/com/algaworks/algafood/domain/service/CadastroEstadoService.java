package com.algaworks.algafood.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {

	private static final String MSG_CIDADE_EM_USO = "Estado de código %d não pode ser removida, pois está em uso";
	private static final String MSG_CIDADE_NAO_ENCONTRADA = "Não existe um cadastro de estado com código %d";

	@Autowired
	private EstadoRepository estadoRepository;

	public Estado salvar(Estado estado) {
		return estadoRepository.save(estado);
	}

	public void excluir(Long estadoId) {
		Optional<Estado> estado = estadoRepository.findById(estadoId);
		try {
			if (estado.isEmpty()) {
				throw new EntidadeNaoEncontradaException(
						String.format("O Estado de código %d não foi encontrado!", estadoId));
			}
			estadoRepository.deleteById(estadoId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Estado de código %d não pode ser removido, pois está em uso!", estadoId));
		}
	}

	public Estado buscarOuFalhar(Long cidadeId) {
		return estadoRepository.findById(cidadeId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(
						String.format(MSG_CIDADE_NAO_ENCONTRADA, cidadeId)));
	}

}
