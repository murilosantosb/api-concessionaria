package com.concessionaria_api.controller;

import com.concessionaria_api.dto.ClienteRequestDTO;
import com.concessionaria_api.dto.ClienteResponseDTO;
import com.concessionaria_api.model.Cliente;
import com.concessionaria_api.repository.ClienteRepository;
import com.concessionaria_api.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/concessionaria/api/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> cadastrar(@Valid @RequestBody ClienteRequestDTO dto) {

        ClienteResponseDTO cliente = clienteService.cadastrar(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listar() {

        return ResponseEntity.ok(clienteService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> buscarPorId(@Valid @PathVariable Long id) {
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@Valid @PathVariable Long id) {
        clienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
