package br.vet.sidekick.poc.controller.dto;

import br.vet.sidekick.poc.model.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@ToString
@Slf4j
public class ProntuarioDto {
    private Long tutor;
    private Long veterinario;
    private Long clinica;
    private Long animal;
    private LocalDateTime dataAtendimento;
    private List<ProcedimentoDto> procedimentos;
    private String tipoCirurgia;
    private String asa;
    private List<PrescricaoDto> prescricoes;
    private String diagnostico;
    private String observacoes;
    private List<DocumentoDto> autorizacoes;
    private Prontuario prontuarioOrigem;
    private List<Map<String, Map<String, String>>> medicamentos;
    public Prontuario convert(String prontuario_codigo) {
        return Prontuario.builder()
                .clinica(Clinica.builder().id(clinica).build())
                .tutor(Tutor.builder().id(tutor).build())
                .veterinario(Veterinario.builder().id(veterinario).build())
                .animal(Animal.builder().id(animal).build())
                .dataAtendimento(LocalDateTime.now())
                .procedimentos(getProcedimentos().stream()
                        .map(proc -> Procedimento.builder()
                                .tipoProcedimento(Procedimento.TipoProcedimento.valueOf(proc.getTipoProcedimento()))
                                .tipo(Procedimento.Tipo.valueOf(proc.getTipo()))
                                .descricao(proc.getDescricao())
                                .build())
                        .collect(Collectors.toList()))
                .cirurgia(Cirurgia.builder()
                        .asa(Cirurgia.ASA.valueOf(asa))
                        .tipo(Cirurgia.TipoCirurgia.valueOf(tipoCirurgia))
                        .build())
                .prescricoes(getPrescricoes().stream()
                        .map(presc -> Prescricao.builder()
                                .descricao(presc.getMedicacao())
                                .build())
                        .collect(Collectors.toList()))
                .diagnostico(getDiagnostico())
                .observacoes(getObservacoes())
//                .documentos(getAutorizacoes().stream()
//                        .map(aut -> Documento.builder()
//                                .build())
//                        .collect(Collectors.toList()))
                .prontuarioOrigem(
                        Prontuario.builder()
                                .codigo(prontuario_codigo)
                                .build()
                )
                .build();
    }
}
