package br.vet.sidekick.poc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cirurgia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private ASA asa;
    private TipoCirurgia tipo;

    @OneToOne
    private Prontuario prontuario;

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
