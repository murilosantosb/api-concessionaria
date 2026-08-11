package com.concessionaria_api.dto;

import com.concessionaria_api.enums.StatusVenda;
import com.concessionaria_api.enums.TipoEstado;
import jakarta.validation.constraints.*;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record CarroResponseDTO(
        Long id,
        String marca,
        String modelo,
        Integer anoFabricacao,
        Integer anoModelo,
        String cor,
        String chassi,
        String placa,
        Integer quilometragem,
        BigDecimal preco,
        TipoEstado tipoEstado,
        StatusVenda statusVenda
) {}
