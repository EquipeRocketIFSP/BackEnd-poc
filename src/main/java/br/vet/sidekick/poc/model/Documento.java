package br.vet.sidekick.poc.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Entity
public class Documento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDateTime dateCreation;
    private Integer versao;

    @ManyToOne
    @JsonBackReference("prontuario_documentos")
    private Prontuario prontuario;

    @ManyToOne
    @JsonBackReference("clinica_documentos")
    private Clinica clinica;

    @ManyToOne
    @JsonBackReference("veterinario_documentos")
    private Veterinario veterinario;

    private LocalDateTime criadoEm;

    @Column(nullable = false)
    private String tipoDocumento;

    public Documento setProntuario(Prontuario p) {
        this.prontuario = p;
        return this;
    }
}
