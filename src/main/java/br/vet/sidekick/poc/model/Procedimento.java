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
public class Procedimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private TipoProcedimento tipoProcedimento;
    private Tipo tipo;

    @ManyToOne
    @JoinColumn(name = "prontuario_id")
    @JsonBackReference("prontuario_procedimentos")
    private Prontuario prontuario;

    @Column(nullable = false)
    private String descricao;

    @ManyToOne
    @JsonBackReference("clinica_procedimentos")
    private Clinica clinica;

    public enum Tipo {
        PRESCRITIVO,
        CONCLUSIVO
    }

    public enum TipoProcedimento {
        IMUNIZACAO,
        EXAME,
        MEDICACAO
    }

}
