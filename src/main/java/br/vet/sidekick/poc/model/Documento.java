package br.vet.sidekick.poc.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Date;

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

    @ManyToOne
    @JsonBackReference("prontuario_documentos")
    private Prontuario prontuario;

    @Column(nullable = false)
    private String caminhoArquivo;

    @ManyToOne
    @JsonBackReference("clinica_documentos")
    private Clinica clinica;

    @ManyToOne
    @JsonBackReference("prontuario_tipoDocumento")
    @JoinColumn(nullable = false)
    private TipoDocumento tipoDocumento;

    private Date criadoEm;
    private String name;
    private Integer versao;

    @ManyToOne
    @JsonBackReference("veterinario_documentos")
    private Veterinario veterinario;

    public Documento setProntuario(Prontuario p) {
        this.prontuario = p;
        return this;
    }
}
