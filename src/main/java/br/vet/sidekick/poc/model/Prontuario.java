package br.vet.sidekick.poc.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
public class Prontuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prontuario", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "animal",nullable = false)
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "clinica",nullable = false)
    private Clinica clinica;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "criado_em", nullable = false)
    private Date criadoEm;

    @OneToOne
    @JoinColumn(name = "prontuario_retorno")
    private Prontuario prontuarioRetorno;

    @ManyToOne
    @JoinColumn(name = "veterinario",nullable = false)
    private Veterinario veterinario;

    @Column(name = "prescricoes")
    private String prescricoes;

    @Column(name = "diagnostico")
    private String diagnostico;

    @Column(name = "observacoes")
    private String observacoes;

    @Column(name = "medicamento")
    private String medicamento;

    @Column(name = "medida")
    private String medida;

    @Column(name = "quantidade")
    private int quantidade;
}
