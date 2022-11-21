package br.vet.sidekick.poc.model;

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
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private LocalDateTime dateCreation;
    private Integer versao;
    @ManyToOne
    private Prontuario prontuario;

    @ManyToOne
    private Clinica clinica;

    @ManyToOne
    private Veterinario veterinario;

    private LocalDateTime criadoEm;

    @Column(nullable = false)
    private String tipoDocumento;

}
