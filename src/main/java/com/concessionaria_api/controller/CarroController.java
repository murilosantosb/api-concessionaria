package com.concessionaria_api.controller;

import com.concessionaria_api.dto.CarroRequestDTO;
import com.concessionaria_api.dto.CarroResponseDTO;
import com.concessionaria_api.model.Carro;
import com.concessionaria_api.model.Cliente;
import com.concessionaria_api.repository.CarroRepository;
import com.concessionaria_api.service.CarroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Carros", description = "Cadastro, consulta e remoção de veículos do estoque.")
@RestController
@RequestMapping("/concessionaria/api/carro")
public class CarroController {

    @Autowired
    private CarroService carroService;

    @Operation(
            summary = "Cadastrar um novo veículo",
            description = "Registra um veículo no estoque da conceissionária."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Veículo cadastradi com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos."),
            @ApiResponse(responseCode = "409", description = "Chassi ou placa já cadastrados")
    })
    @PostMapping
    public ResponseEntity<CarroResponseDTO> cadastrar(@Valid @RequestBody CarroRequestDTO carro) {
        CarroResponseDTO novoCarro = carroService.cadastrar(carro);

        return ResponseEntity.status(HttpStatus.CREATED).body(novoCarro);
    }

    @Operation(
            summary = "Busca de veículos",
            description = "Buscar todos os veiculos e também filtra por cor e ano do modelo"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso.")
    })
    @GetMapping
    public ResponseEntity<List<CarroResponseDTO>> buscar(
            @RequestParam(required = false) String cor,
            @RequestParam(required = false) Integer anoModelo
            ) {

        return ResponseEntity.ok(carroService.buscarComFiltro(cor, anoModelo));
    }

    @Operation(
            summary = "Buscar Veículos",
            description = "Buscar veículos por id"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Veículo encontrado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Veículo não encontrado.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CarroResponseDTO> buscarPorId(@Valid @PathVariable Long id) {
        return ResponseEntity.ok(carroService.buscarPorId(id));
    }

    @Operation(
            summary = "Deletar um Veículo",
            description = "Deletar um veículo pelo seu ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Veiculo removido com sucesso."),
            @ApiResponse(responseCode = "404", description = "Veículo não encontrado.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@Valid @PathVariable Long id) {
        carroService.deletar(id);

        return ResponseEntity.noContent().build();
    }
}
