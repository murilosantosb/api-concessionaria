package com.concessionaria_api.repository;

import com.concessionaria_api.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarroRepository extends JpaRepository<Carro, Long> {
    boolean existsByChassi(String chassi);
    boolean existsByPlaca(String placa);


    @Query("""
            SELECT c FROM Carro c
            WHERE (:cor IS NULL OR c.cor = :cor)
            AND (:anoModelo IS NULL OR c.anoModelo = :anoModelo)
            """)
    List<Carro> buscarComFiltro(@Param("cor") String cor, @Param("anoModelo") Integer anoModelo);
}
