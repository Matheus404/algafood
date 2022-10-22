package com.algaworks.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
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
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontrada;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private CadastroCozinhaService cadastroCozinhaService;
	
	@GetMapping
	public List<Cozinha> listar() {
		return cozinhaRepository.findAll();
	}
	
	@GetMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> buscar(@PathVariable("cozinhaId") Long cozinhaId) {
		return cozinhaRepository.findById(cozinhaId)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public ResponseEntity<Cozinha> adicionar(@RequestBody Cozinha cozinha) {
		return new ResponseEntity<Cozinha>(cadastroCozinhaService.salvar(cozinha),
				HttpStatus.CREATED);
	}
	
	@PutMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha) {
		 Optional<Cozinha> cozinhaAtual = cozinhaRepository.findById(cozinhaId);
		 
		 if(cozinhaAtual.isPresent()) {
			 //cozinhaAtual.get().setNome(cozinha.getNome());
			 BeanUtils.copyProperties(cozinha, cozinhaAtual.get(), "id");
			 cadastroCozinhaService.salvar(cozinhaAtual.get());
			 return new ResponseEntity<Cozinha>(cozinhaAtual.get(), HttpStatus.CREATED);
		 }
		 
		 return  ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{cozinhaId}")
	public ResponseEntity<Void> delete(@PathVariable Long cozinhaId) {
		try {
			cadastroCozinhaService.excluir(cozinhaId);
			return ResponseEntity.noContent().build();
			
		} catch (EntidadeNaoEncontrada e) {
			return ResponseEntity.notFound().build();
			
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}
	
}
