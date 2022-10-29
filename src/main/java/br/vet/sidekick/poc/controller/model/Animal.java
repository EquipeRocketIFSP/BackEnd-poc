package br.vet.sidekick.poc.controller.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private Long clinica;

    private String especie;

    private String forma_identificacao;

    private Integer idade;

    private Long mae;

    private String nome;

    private Long pai;

    private String pelagem;

    private String raca;

    private String sexo;


}
