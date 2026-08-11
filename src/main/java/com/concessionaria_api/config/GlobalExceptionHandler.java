package com.concessionaria_api.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidacao(MethodArgumentNotValidException ex) {
        List<Map<String, String>> erros = ex.getBindingResult().getFieldErrors().stream()
                .map(erro -> Map.of("campo", erro.getField(), "mensagem", erro.getDefaultMessage()))
                .toList();

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", 400);
        body.put("erros", erros);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> handleJsonInvalido(HttpMessageNotReadableException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", 400);
        body.put("erros", List.of(Map.of(
                "campo", "body",
                "mensagem", "Corpo da requisição inválido. Verifique os tipos e o formato dos campos enviados."
        )));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> handleNaoEncontrado(RecursoNaoEncontradoException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", 404);
        body.put("mensagem", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(RecursoDuplicadoException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicado(RecursoDuplicadoException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", 409);
        body.put("mensagem", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }
}