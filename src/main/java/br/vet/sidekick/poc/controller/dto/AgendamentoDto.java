package br.vet.sidekick.poc.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AgendamentoDto {
    private Long animal;
    private Long clinica;
    private Date criadoEm;
    private LocalDateTime dataConsulta;
    private String tipoConsulta;
}
