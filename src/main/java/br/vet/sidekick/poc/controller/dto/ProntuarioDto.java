package br.vet.sidekick.poc.controller.dto;

import br.vet.sidekick.poc.model.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@ToString
public class ProntuarioDto {

//        "veterinario": "1",
//        "animal": "1",
//        "prescricoes": ["string"],
//        "medicamentos": [{
//          "nomeMedicamento" : {
//            "quantidade": 1,
//            "medida": "ml"
//          }
//        }],
//        "dataAtendimento": "2022-11-07T00:00:00",
//        "tipo-cirugia": enum{CASTRACAO,ORTOPEDICA,OFTALMICA,TECIDOS_MOLES,ODONTOLOGICA},
//        "asa": enum{ASA1,ASA2,ASA3,ASA4}
//        "diagnostico": "string",
//        "observacoes": "string",
//        "categoria-paciente": "",
//        "exames": ["string"], // será tratado como procedimento
//        "procedimentos": [{
//          "tipoProcedimento" : enum{IMUNIZACAO,EXAME,MEDICACAO},
//          "tipo": enum{PRESCRITIVO,CONCLUSIVO},
//          "descricao": "string"
//        }]
    private Long tutor;
    private Long veterinario;
    private Long animal;
    private LocalDateTime dataAtendimento;
    private List<ProcedimentoDto> procedimentos;
    @JsonProperty("tipo-cirurgia")
    private String tipoCirurgia;
    private String asa;
    private List<PrescricaoDto> prescricoes;
    private String diagnostico;
    private String observacoes;
    private List<DocumentoDto> autorizacoes;
    private Prontuario prontuarioOrigem;
    private List<Map<String, Map<String, String>>> medicamentos;

    public Prontuario convert() {
        return Prontuario.builder()
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
                .prontuarioOrigem(getProntuarioOrigem())
                .build();
    }
}
