package com.concessionaria_api.controller;

import com.concessionaria_api.dto.CarroRequestDTO;
import com.concessionaria_api.dto.CarroResponseDTO;
import com.concessionaria_api.model.Carro;
import com.concessionaria_api.model.Cliente;
import com.concessionaria_api.repository.CarroRepository;
import com.concessionaria_api.service.CarroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/concessionaria/api/carro")
public class CarroController {

    @Autowired
    private CarroService carroService;

    @PostMapping
    public ResponseEntity<CarroResponseDTO> cadastrar(@Valid @RequestBody CarroRequestDTO carro) {
        CarroResponseDTO novoCarro = carroService.cadastrar(carro);

        return ResponseEntity.status(HttpStatus.CREATED).body(novoCarro);
    }

    @GetMapping
    public ResponseEntity<List<CarroResponseDTO>> listar() {
        return ResponseEntity.ok(carroService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarroResponseDTO> buscarPorId(@Valid @PathVariable Long id) {
        return ResponseEntity.ok(carroService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@Valid @PathVariable Long id) {
        carroService.deletar(id);

        return ResponseEntity.noContent().build();
    }
}
