package br.vet.sidekick.poc.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CadastroAgendamentoDto {
    @JsonProperty("animal")
    private Long animal;

    @JsonProperty("data-consulta")
    private String dataConsulta;

    @JsonProperty("tipo-consulta")
    private String tipoConsulta;
}
