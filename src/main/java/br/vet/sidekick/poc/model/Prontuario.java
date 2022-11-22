package br.vet.sidekick.poc.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Entity
public class Prontuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;
    private Integer versao;

    @ManyToOne
    @JoinColumn(name = "tutor_id")
    @JsonBackReference("tutor_prontuarios")
    private Tutor tutor;

    @ManyToOne
    @JsonBackReference("animal_prontuarios")
    private Animal animal;

    @ManyToOne
    @JsonBackReference("clinica_prontuarios")
    private Clinica clinica;

    @Column(nullable = false)
    private LocalDateTime criadoEm;

    @ManyToOne
    @JsonBackReference("veterinario_prontuarios")
    private Veterinario veterinario;
    private LocalDateTime dataAtendimento;

    @OneToMany(mappedBy = "prontuario")
    @JsonManagedReference("prontuario_procedimentos")
    private List<Procedimento> procedimentos = new java.util.ArrayList<>();

    @OneToOne
    private Cirurgia cirurgia;

    @OneToMany(mappedBy = "prontuario")
    @JsonManagedReference("prontuario_prescricoes")
    private List<Prescricao> prescricoes;

    private String diagnostico;

    private String observacoes;

    @OneToMany(mappedBy = "id")
    @JsonManagedReference("prontuario_documentos")
    private List<Documento> documentos;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "origem_id", unique = true)
    private Prontuario prontuarioOrigem;
    public void applyDifference(Prontuario that) {
        if (!Objects.equals(codigo, that.codigo)) codigo = that.codigo;
        if (!Objects.equals(tutor, that.tutor)) tutor = that.tutor;
        if (!Objects.equals(veterinario, that.veterinario)) veterinario = that.veterinario;
        if (!Objects.equals(clinica, that.clinica)) clinica = that.clinica;
        if (!Objects.equals(animal, that.animal)) animal = that.animal;
        if (!Objects.equals(criadoEm, that.criadoEm)) criadoEm = that.criadoEm;
        if (!Objects.equals(dataAtendimento, that.dataAtendimento)) dataAtendimento = that.dataAtendimento;
        if (!Objects.equals(procedimentos, that.procedimentos)) procedimentos = that.procedimentos;
        if (!Objects.equals(cirurgia, that.cirurgia)) cirurgia = that.cirurgia;
        if (!Objects.equals(prescricoes, that.prescricoes)) prescricoes = that.prescricoes;
        if (!Objects.equals(diagnostico, that.diagnostico)) diagnostico = that.diagnostico;
        if (!Objects.equals(observacoes, that.observacoes)) observacoes = that.observacoes;
        if (!Objects.equals(documentos, that.documentos)) documentos = that.documentos;
        if (!Objects.equals(prontuarioOrigem, that.prontuarioOrigem)) prontuarioOrigem = that.prontuarioOrigem;
    }

    public void incrementVersion() {
        id = null;
        versao += 1;
    }

    public static String createCodigo(LocalDateTime now) {
        return "VT-P-"+now.format(DateTimeFormatter.ofPattern("yyyy_MM_dd_hh_mm_ss"))+".pdf";
    }
    public Prontuario setCirurgia(Cirurgia cirurgia){
        this.cirurgia = cirurgia;
        return this;
    }

    public Prontuario setTutor(Tutor tutor){
        this.tutor = tutor;
        return this;
    }

    public Prontuario addDocumentoPdf(Documento documento) {
        documentos = Arrays.asList(documento);
        return this;
    }

    public Prontuario setDocumentoDetails(Documento documento){
        return addDocumentoPdf(documento)
                .setCodigo(documento.getName())
                .setVersao(documento.getVersao())
                .setCriadoEm(documento.getCriadoEm());
    }

    private Prontuario setCriadoEm(LocalDateTime criadoEm) {
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

}
