package br.vet.sidekick.poc.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

public class Estoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private Integer clinica;

    private Long entrada_qtd;

    private String lote;

    private Long medicamento;

    private Integer quantidade;

    private LocalDateTime validade;

}
