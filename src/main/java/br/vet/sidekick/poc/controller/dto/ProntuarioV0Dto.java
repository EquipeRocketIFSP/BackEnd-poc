package br.vet.sidekick.poc.controller.dto;


import lombok.Getter;

@Getter
public class ProntuarioV0Dto {
    private Long clinica;
    private Long veterinario;
    private Long animal;
    private String diagnostico;
    private String observacoes;
    private String medicamento;
    private String medida;
    private String tipoCirugia;
    private String asa;
    private String exames;
    private String procedimento;
    private String prescricoes;
    private Integer quantidade;
}
