package br.vet.sidekick.poc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.security.cert.CertPathBuilder;
import java.util.List;

@Entity
@Getter
@Builder
public class Procedimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private TipoProcedimento tipoProcedimento;
    private String descricao;
    private Tipo tipo;

    @ManyToOne
    @JoinColumn(name = "prontuario_id")
    @JsonIgnore
    private Prontuario prontuario;


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
