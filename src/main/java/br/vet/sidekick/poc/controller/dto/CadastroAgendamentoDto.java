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

    public static CadastroAgendamentoDto getMock(){
        return CadastroAgendamentoDto.builder()
                .animal(1L)
                .dataConsulta("2022-10-29T15:31")
                .tipoConsulta("Tipo da consulta")
                .build();
    }
}
