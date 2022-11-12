package br.vet.sidekick.poc.model;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Telefone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String numeracao;
}
