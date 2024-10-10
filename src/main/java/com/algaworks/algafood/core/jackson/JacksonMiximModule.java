package com.algaworks.algafood.core.jackson;

import com.algaworks.algafood.api.model.mixim.CidadeMixim;
import com.algaworks.algafood.api.model.mixim.CozinhaMixin;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.api.model.mixim.RestauranteMixim;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

@Component
public class JacksonMiximModule extends SimpleModule {

    private static final long serialVersionUID = 1L;

    public JacksonMiximModule() {
        //setMixInAnnotation(Restaurante.class, RestauranteMixim.class);
        //setMixInAnnotation(Cidade.class, CidadeMixim.class);
        //setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
    }

}
