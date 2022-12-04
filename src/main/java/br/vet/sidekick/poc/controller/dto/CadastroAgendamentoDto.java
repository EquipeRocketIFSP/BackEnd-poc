package br.vet.sidekick.poc.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CadastroAgendamentoDto {
    private Long clinica;
    private Long animal;
    private String dataConsulta;
    private String tipoConsulta;
}
