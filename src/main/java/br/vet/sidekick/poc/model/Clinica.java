package br.vet.sidekick.poc.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Clinica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String bairro;

    private String celular;

    private String cep;

    private String cidade;

    private String cnpj;

    private String email;

    private String estado;

    private String logradouro;

    private Integer numero;

    private String razao_social;

    private String responsavel_tecnico;

    private String telefone;

}
