package br.vet.sidekick.poc.model;

import lombok.*;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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

    private String codigo;

    private Integer versao;

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
    public void applyDifference(Prontuario that) {
        if (!Objects.equals(codigo, that.codigo)) codigo = that.codigo;
        if (!Objects.equals(tutor, that.tutor)) tutor = that.tutor;
        if (!Objects.equals(veterinario, that.veterinario)) veterinario = that.veterinario;
        if (!Objects.equals(clinica, that.clinica)) clinica = that.clinica;
        if (!Objects.equals(animal, that.animal)) animal = that.animal;
        if (!Objects.equals(createdAt, that.createdAt)) createdAt = that.createdAt;
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
        return "VT-P-"+new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(now);
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
        documentos.add(documento);
        return this;
    }

    public Prontuario setDocumentoDetails(Documento documento){
        return addDocumentoPdf(documento)
                .setCodigo(documento.getName())
                .setVersao(documento.getVersao());
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
