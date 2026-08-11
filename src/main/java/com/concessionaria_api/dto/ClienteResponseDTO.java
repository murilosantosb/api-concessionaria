package com.concessionaria_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.hibernate.validator.constraints.br.CPF;

@Builder
public record ClienteResponseDTO(
        Long id,
        String nome,
        String email,
        String cpf,
        String telefone
) {}
