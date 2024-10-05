package com.algaworks.algafood.api.model.mixim;

import com.algaworks.algafood.domain.model.Estado;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class CidadeMixim {


    public abstract class CidadeMixin {

        @JsonIgnoreProperties(value = "nome", allowGetters = true)
        private Estado estado;

    }

}
