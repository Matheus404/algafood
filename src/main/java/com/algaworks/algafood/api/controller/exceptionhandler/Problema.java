package com.algaworks.algafood.api.controller.exceptionhandler;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@Builder
public class Problema {

    private LocalDateTime dataHora;
    private String mensagem;

}
