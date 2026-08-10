package com.concessionaria_api.controller;

import com.concessionaria_api.model.Carro;
import com.concessionaria_api.model.Cliente;
import com.concessionaria_api.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/concessionaria/api/carro")
public class CarroController {

    @Autowired
    private CarroRepository carroRepository;

    @PostMapping
    public ResponseEntity<Carro> cadastrar(@RequestBody Carro carro) {
        Carro novoCarro = Carro.builder()
                .marca(carro.getMarca())
                .modelo(carro.getModelo())
                .anoFabricacao(carro.getAnoFabricacao())
                .anoModelo(carro.getAnoModelo())
                .cor(carro.getCor())
                .chassi(carro.getChassi())
                .placa(carro.getPlaca())
                .quilometragem(carro.getQuilometragem())
                .preco(carro.getPreco())
                .tipoEstado(carro.getTipoEstado())
                .statusVenda(carro.getStatusVenda())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(novoCarro);
    }

    @GetMapping
    public ResponseEntity<List<Carro>> listar() {
        return ResponseEntity.ok(carroRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carro> buscarPorId(@PathVariable Long id) {
        Carro carro = carroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carro não encontrado."));

        return ResponseEntity.ok(carro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        carroRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
