package com.concessionaria_api.model;

import com.concessionaria_api.enums.StatusVenda;
import com.concessionaria_api.enums.TipoEstado;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Carro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false)
    private Integer anoFabricacao;

    @Column(nullable = false)
    private Integer anoModelo;

    @Column(nullable = false)
    private String cor;

    @Column(nullable = false, unique = true)
    private String chassi;

    @Column(unique = true)
    private String placa;

    @Column(nullable = false)
    private Integer quilometragem;

    @Column(nullable = false)
    private BigDecimal preco;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoEstado tipoEstado;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusVenda statusVenda;
}
