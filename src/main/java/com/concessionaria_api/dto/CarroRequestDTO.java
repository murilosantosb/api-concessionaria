package com.concessionaria_api.dto;

import com.concessionaria_api.enums.TipoEstado;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record CarroRequestDTO(

        @NotBlank(message = "Marca é obrigatória.")
        String marca,

        @NotBlank(message = "Modelo é obrigatório.")
        String modelo,

        @NotNull(message = "Ano de fabricação é obrigatório.")
        @Min(value = 1950, message = "Ano de fabricação inválido.")
        @Max(value = 2026, message = "Ano de fabricação não pode ser maior que 2026.")
        Integer anoFabricacao,

        @NotNull(message = "Ano do modelo é obrigatório.")
        @Min(value = 1950, message = "Ano do modelo inválido.")
        @Max(value = 2027, message = "Ano do modelo não pode ser maior que 2027.")
        Integer anoModelo,

        @NotBlank(message = "Cor é obrigatória.")
        String cor,

        @NotBlank(message = "Chassi é obrigatório.")
        String chassi,

        String placa,

        @NotNull(message = "Quilometragem é obrigatória.")
        @PositiveOrZero(message = "Quilometragem não pode ser negativa.")
        Integer quilometragem,

        @NotNull(message = "Preço é obrigatório.")
        @DecimalMin(value = "0.01", message = "Preço deve ser maior que 0.")
        BigDecimal preco,

        @NotNull(message = "Tipo do carro é obrigatório.")
        TipoEstado tipoEstado

) {}
