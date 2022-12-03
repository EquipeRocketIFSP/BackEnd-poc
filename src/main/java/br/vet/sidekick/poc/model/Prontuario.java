package br.vet.sidekick.poc.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
//@Setter
@Getter
@ToString
@Entity
public class Prontuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    @JsonManagedReference("prontuario_documentos")
    private List<Documento> documentos;

    @ManyToOne
    @JoinColumn(name = "tutor_id", nullable = false)
    private Tutor tutor;

    @ManyToOne
    @JoinColumn(name = "animal_id", nullable = false)
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "clinica_id" ,nullable = false)
    private Clinica clinica;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date criadoEm;

    @OneToOne
    @JoinColumn(name = "prontuario_retorno")
    private Prontuario prontuarioRetorno;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Veterinario veterinario;

    private LocalDate dataAtendimento;
    private String prescricoes;
    private String diagnostico;
    private String observacoes;
    private String medicamento;
    private String medida;
    private Integer quantidade;
    private Integer versao;
    private String codigo;


    private Prontuario setCriadoEm(Date criadoEm) {
        this.criadoEm = criadoEm;
        return this;
    }

    private Prontuario setCodigo(String codigo) {
        this.codigo = codigo;
        return this;
    }

    private Prontuario setVersao(int versao) {
        this.versao = versao;
        return this;
    }

    public Prontuario addDocumentoPdf(Documento documento) {
        documentos = Arrays.asList(documento);
        return this;
    }

    public static String createCodigo(LocalDateTime now) {
        // exemplo: VT-P-2022_12_03_02_19_20.pdf
        return "VT-P-"+now.format(DateTimeFormatter.ofPattern("yyyy_MM_dd_hh_mm_ss"));
    }

    public Prontuario setDocumentoDetails(Documento documento){
        return addDocumentoPdf(documento)
                .setCodigo(documento.getName())
                .setVersao(documento.getVersao())
                .setCriadoEm(documento.getCriadoEm());
    }

    public Prontuario setClinica(Clinica clinica) {
        this.clinica = clinica;
        return this;
    }

    public Prontuario setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
        return this;
    }

    public Prontuario setAnimal(Animal animal) {
        this.animal = animal;
        return this;
    }
}
