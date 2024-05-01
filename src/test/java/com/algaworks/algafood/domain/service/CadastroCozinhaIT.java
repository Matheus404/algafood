package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.util.DatabaseCleaner;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.flywaydb.core.Flyway;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@TestPropertySource("/application-test.properties")
class CadastroCozinhaIT {

    @LocalServerPort
    private int port;
    @Autowired
    private CadastroCozinhaService cadastroCozinhaService;
    //@Autowired
    //private Flyway flyway;
    //@Autowired
    //private DatabaseCleaner databaseCleaner;

    @BeforeEach
    void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/cozinhas";

        //flyway.migrate();
        //databaseCleaner.clearTables();
    }

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

        RestAssured.given()
                    .accept(ContentType.JSON)
                .when()
                    .get()
                .then()
                    .statusCode(200);
    }

    @Test
    void deveRetornar4Cozinhas_QuandoConsultarAListaDeCozinhas() {

        RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .body("", Matchers.hasSize(4))
                .body("nome", Matchers.hasItems("Indiana", "Taillandesa"));
    }

    @Test
    void deveRetornarStatus201_QuandoCadastrarCozinha() {

        RestAssured.given()
                .body("{ \"nome\": \"Chinesa\" }")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    void deveRetornarRespostaEStatusCorretos_QuandoConsultarCozinhaExistente() {

        RestAssured.given()
                .pathParam("cozinhaId", 2)
                .accept(ContentType.JSON)
                .when()
                .get("/{cozinhaId}")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("nome", Matchers.equalTo("Indiana"));
    }

    @Test
    void deveRetornarStatus404_QuandoConsultarCozinhaInexistente() {

        RestAssured.given()
                .pathParam("cozinhaId", 1000)
                .accept(ContentType.JSON)
                .when()
                .get("/{cozinhaId}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

}