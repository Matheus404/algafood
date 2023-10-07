package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CadastroCozinhaIT {

    @LocalServerPort
    private int port;
    @Autowired
    private CadastroCozinhaService cadastroCozinhaService;

    @Test
    void deveSalvarCozinhaComSucesso() {
        Cozinha cozinha = new Cozinha();
        cozinha.setNome("Chinesa");

        cozinha = cadastroCozinhaService.salvar(cozinha);

        assertThat(cozinha).isNotNull();
        assertThat(cozinha.getId()).isNotNull();
    }

    @Test
    void deveFalharAoSalvarCozinhaSemNome_Junit5() {
        Cozinha cozinha = new Cozinha();
        cozinha.setNome(null);

        ConstraintViolationException erroEsperado = assertThrows(ConstraintViolationException.class, () -> {
            cadastroCozinhaService.salvar(cozinha);
        });

        assertThat(erroEsperado).isNotNull();
    }

    @Test
    void deveFalharAoExcluirCozinhaEmUso() {
        EntidadeEmUsoException entidadeEmUsoException = assertThrows(EntidadeEmUsoException.class, () -> {
            cadastroCozinhaService.excluir(51L);
        });

        assertThat(entidadeEmUsoException).isNotNull();
    }

    @Test
    void deveFalharAoExcluirCozinhaInexistente() {
        EntidadeNaoEncontradaException entidadeNaoEncontradaException = assertThrows(EntidadeNaoEncontradaException.class, () -> {
            cadastroCozinhaService.excluir(100L);
        });

        assertThat(entidadeNaoEncontradaException).isNotNull();
    }

    /*
     * Integration tests
     * Using rest-assured
     */
    @Test
    void deveRetornarStatus200_QuandoConsultarAListaDeCozinhas() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        RestAssured.given()
                    .basePath("/cozinhas")
                    .port(port)
                    .accept(ContentType.JSON)
                .when()
                    .get()
                .then()
                    .statusCode(200);
    }



}