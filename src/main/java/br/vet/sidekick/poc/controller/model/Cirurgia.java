package br.vet.sidekick.poc.controller.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Cirurgia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String categoria_paciente;

    private Long clinica;

    private Long prontuario;

    private Long tipo_cirurgia;

}
