package br.vet.sidekick.poc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Prontuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tutor_id")
    private Tutor tutor;

    @ManyToOne
    @JoinColumn(name = "veterinario_id")
    private Veterinario veterinario;

    @ManyToOne
    @JoinColumn(name = "clinica_id")
    private Clinica clinica;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime dataAtendimento;

    @OneToMany(mappedBy = "prontuario")
    private List<Procedimento> procedimentos = new java.util.ArrayList<>();

    @OneToOne
    private Cirurgia cirurgia;

    @OneToMany(mappedBy = "prontuario")
    private List<Prescricao> prescricoes;

    private String diagnostico;

    private String observacoes;

    @OneToMany(mappedBy = "prontuario")
    private List<Documento> documentos;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "origem_id", unique = true)
    private Prontuario prontuarioOrigem;


    public Prontuario setCirurgia(Cirurgia cirurgia){
        this.cirurgia = cirurgia;
        return this;
    }

    public Prontuario setTutor(Tutor tutor){
        this.tutor = tutor;
        return this;
    }
}
