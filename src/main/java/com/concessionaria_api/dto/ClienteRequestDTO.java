package com.concessionaria_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record ClienteRequestDTO(
    @NotBlank(message = "Campo nome não pode está vazio.")
    String nome,

    @NotBlank(message = "Campo email não pode está vazio.")
    @Email
    String email,

    @NotBlank(message = "Campo CPF não pode está vazio.")
    @CPF
    String cpf,

    @NotBlank(message = "Campo telefone não pode está vazio.")
    String telefone
) {}
