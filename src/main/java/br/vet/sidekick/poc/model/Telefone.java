package br.vet.sidekick.poc.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Telefone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeracao;
    private String tipo;
    @ManyToOne
    @JsonBackReference("clinica_telefones")
    private Clinica clinica;
}
