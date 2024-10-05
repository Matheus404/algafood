package com.algaworks.algafood.core.jackson;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.model.mixim.RestauranteMixim;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

@Component
public class JacksonMiximModule extends SimpleModule {

    private static final long serialVersionUID = 1L;

    public JacksonMiximModule() {
        setMixInAnnotation(Restaurante.class, RestauranteMixim.class);
    }

}
