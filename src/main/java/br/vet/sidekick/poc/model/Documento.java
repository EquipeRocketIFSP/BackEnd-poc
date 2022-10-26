package br.vet.sidekick.poc.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

public class Documento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private Long id_prontuario;

    private String caminho_arquivo;

    private Long clinica;

    private LocalDateTime criado_em;

    private Long tipo_documento;

}
