package com.concessionaria_api.service;

import com.concessionaria_api.config.RecursoDuplicadoException;
import com.concessionaria_api.config.RecursoNaoEncontradoException;
import com.concessionaria_api.dto.CarroRequestDTO;
import com.concessionaria_api.dto.CarroResponseDTO;
import com.concessionaria_api.enums.StatusVenda;
import com.concessionaria_api.enums.TipoEstado;
import com.concessionaria_api.model.Carro;
import com.concessionaria_api.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    public CarroResponseDTO cadastrar(CarroRequestDTO dto) {

        if(carroRepository.existsByChassi(dto.chassi())) {
            throw new RecursoDuplicadoException("Já existe um carro cadastrado com esse chassi.");
        }

        if (dto.placa() != null && carroRepository.existsByPlaca(dto.placa())) {
            throw new RecursoDuplicadoException("Já existe um carro cadastrado com essa placa.");
        }

        Carro carro = Carro.builder()
                .marca(dto.marca())
                .modelo(dto.modelo())
                .anoFabricacao(dto.anoFabricacao())
                .anoModelo(dto.anoModelo())
                .cor(dto.cor())
                .chassi(dto.chassi())
                .placa(dto.placa())
                .quilometragem(dto.quilometragem())
                .preco(dto.preco())
                .tipoEstado(dto.tipoEstado())
                .statusVenda(StatusVenda.DISPONIVEL)
                .build();

        return toResponse(carroRepository.save(carro));
    }

    public List<CarroResponseDTO> listar () {
        List<Carro> carro = carroRepository.findAll();

        return carro.stream()
                .map((s) -> toResponse(s)).toList();
    }

    public CarroResponseDTO buscarPorId(Long id) {
        Carro carro = carroRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Carro não encontrado."));

        return new CarroResponseDTO(
                carro.getId(),
                carro.getMarca(),
                carro.getModelo(),
                carro.getAnoFabricacao(),
                carro.getAnoModelo(),
                carro.getCor(),
                carro.getChassi(),
                carro.getPlaca(),
                carro.getQuilometragem(),
                carro.getPreco(),
                carro.getTipoEstado(),
                carro.getStatusVenda()
        );
    }

    public Void deletar(Long id) {
        CarroResponseDTO carro = buscarPorId(id);

        if (carro == null) {
            throw new RecursoNaoEncontradoException("Carro não encontrado.");
        } else {
            carroRepository.deleteById(id);
        }
        return null;
    }

    public CarroResponseDTO toResponse(Carro carro) {
        return CarroResponseDTO.builder()
                .id(carro.getId())
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
    }
}
