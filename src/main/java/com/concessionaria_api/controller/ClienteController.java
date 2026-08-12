package com.concessionaria_api.controller;

import com.concessionaria_api.dto.ClienteRequestDTO;
import com.concessionaria_api.dto.ClienteResponseDTO;
import com.concessionaria_api.model.Cliente;
import com.concessionaria_api.repository.ClienteRepository;
import com.concessionaria_api.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Clientes", description = "Cadastro, consulta e remoção de clientes")
@RestController
@RequestMapping("/concessionaria/api/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;


    @Operation(
            summary = "Cadastrar Cliente",
            description = "Registrar cliente na conceissionária."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cliente cadastrado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos."),
            @ApiResponse(responseCode = "409", description = "Cliente já cadastrado, CPF existente no sistema.")
    })
    @PostMapping
    public ResponseEntity<ClienteResponseDTO> cadastrar(@Valid @RequestBody ClienteRequestDTO dto) {

        ClienteResponseDTO cliente = clienteService.cadastrar(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }

    @Operation(
            summary = "Listar clientes",
            description = "Listar clientes da conceissionária"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso.")
    })
    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listar() {

        return ResponseEntity.ok(clienteService.listar());
    }

    @Operation(
            summary = "Buscar Cliente",
            description = "Buscar cliente pelo seu ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cliente encontrado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Cliente não foi encontrado.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> buscarPorId(@Valid @PathVariable Long id) {
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }

    @Operation(
            summary = "Remover Cliente",
            description = "Remover cliente da conceissionária"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cliente removido com sucesso."),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@Valid @PathVariable Long id) {
        clienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
