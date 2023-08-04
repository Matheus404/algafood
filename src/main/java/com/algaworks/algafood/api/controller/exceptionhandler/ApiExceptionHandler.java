package com.algaworks.algafood.api.controller.exceptionhandler;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> tratarEntidadeNaoEncontradaException(
            EntidadeNaoEncontradaException e) {
        Problema problema = Problema.builder()
                .dataHora(LocalDateTime.now())
                .mensagem(e.getMessage()).build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(problema);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> tratarNegocioException(NegocioException e) {
        Problema problema = Problema.builder()
                .dataHora(LocalDateTime.now())
                .mensagem(e.getMessage()).build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(problema);
    }

//    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
//    public ResponseEntity<?> tratarHttpMediaTypeNotSupportedException() {
//        Problema problema = Problema.builder()
//                .dataHora(LocalDateTime.now())
//                .mensagem("O tipo de mídia não é aceito.").build();
//
//        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
//                .body(problema);
//    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> tretarEntidadeEmUsoException(EntidadeEmUsoException e) {
        Problema problema = Problema.builder()
                .dataHora(LocalDateTime.now())
                .mensagem(e.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(problema);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Problema problema = Problema.builder()
                .dataHora(LocalDateTime.now())
                .mensagem("O tipo de mídia não é aceito.").build();

        return handleExceptionInternal(ex, problema, headers, status, request);
    }

}
