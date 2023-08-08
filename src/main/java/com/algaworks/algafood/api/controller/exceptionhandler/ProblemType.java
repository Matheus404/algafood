package com.algaworks.algafood.api.controller.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    MENSAGEM_IMCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem imcompreensível"),
    ENTIDADE_NAO_ENCONTRADA("/endidade-nao-encontrada", "Entidade não encontrada"),
    ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
    ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio");

    private String title;
    private String uri;

    ProblemType(String path, String title) {
        this.uri = "https://algafood.com.br" + path;
        this.title = title;
    }

}
