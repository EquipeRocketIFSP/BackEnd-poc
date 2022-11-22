package br.vet.sidekick.poc.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Entity
public class Cirurgia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private ASA asa;
    private TipoCirurgia tipo;

    @OneToOne
    @JoinColumn(name = "id")
    @JsonBackReference("prontuario_cirurgias")
    private Prontuario prontuario;

    @ManyToOne
    @JsonBackReference("clinica_cirurgias")
    private Clinica clinica;

    public void setProntuario(Prontuario prontuario) {
        this.prontuario = prontuario;
    }

    public enum ASA {
        ASA1,
        ASA2,
        ASA3,
        ASA4
    }
    public enum TipoCirurgia {
        CASTRACAO,
        ORTOPEDICA,
        OFTALMICA,
        TECIDOS_MOLES,
        ODONTOLOGICA
    }
}
