package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
	
	@Autowired
	public RestauranteRepository restauranteRepository;
	
	@GetMapping
	public List<Restaurante> listar() {
		return restauranteRepository.findAll();
	}
	
	@GetMapping("/restauranteId")
	public ResponseEntity<Restaurante> buscar(@PathVariable Long restauranteId) {
		return restauranteRepository.findById(restauranteId)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

}
