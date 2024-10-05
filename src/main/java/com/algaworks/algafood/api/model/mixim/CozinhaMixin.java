package com.algaworks.algafood.api.model.mixim;

import com.algaworks.algafood.domain.model.Restaurante;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class CozinhaMixin {

	@JsonIgnore
	private List<Restaurante> restaurantes;
	
}