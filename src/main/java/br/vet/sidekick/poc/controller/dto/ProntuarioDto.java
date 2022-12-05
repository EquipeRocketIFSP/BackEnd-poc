package br.vet.sidekick.poc.controller.dto;

import br.vet.sidekick.poc.model.*;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class ProntuarioDto {
    private Long clinica;
    private Long veterinario;
    private Long animal;
    private String tutor;
    private String diagnostico;
    private String observacoes;
    private String medicamento;
    private String medida;
    private String tipoCirurgia;
    private String asa;
    private String exames;
    private String procedimento;
    private String prescricoes;
    private int quantidade;

    public Prontuario convert() {
        return Prontuario.builder()
                .diagnostico(this.diagnostico)
                .observacoes(this.observacoes)
                .medicamento(this.medicamento)
                .medida(this.medida)
                .prescricoes(this.prescricoes)
                .quantidade(this.quantidade)
                .tutor(Tutor.builder().cpf(tutor).build())
                .dataAtendimento(LocalDateTime.now())
                .build();
    }

//    public String getCertvetCode() {
//        return certvetCode;
//    }
}
