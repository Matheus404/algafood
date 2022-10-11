package com.algaworks.algafood.domain.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Cozinha {
	
	@Id
	private Long id;
	
	private String nome;
	
}
