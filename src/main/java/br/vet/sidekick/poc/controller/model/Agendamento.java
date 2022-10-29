package br.vet.sidekick.poc.controller.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private Long animal;

    private Long clinica;

    private LocalDateTime criado_em;

    private LocalDateTime data_consulta;

    private String tipo;

}
