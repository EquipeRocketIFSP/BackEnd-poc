package br.vet.sidekick.poc.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String bairro;

    private String celular;

    private String cep;

    private String cidade;

    private Long clinica;

    private String cpf;

    private String estado;

    private String email;

    private String logradouro;

    private String nome;

    private Integer numero;

    private String rg;

    private String telefone;

}
