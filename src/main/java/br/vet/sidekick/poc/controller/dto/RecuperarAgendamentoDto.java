package br.vet.sidekick.poc.controller.dto;

import br.vet.sidekick.poc.model.Animal;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@Getter
public class RecuperarAgendamentoDto {
    private String animal;
    private LocalDateTime dataConsulta;
    private Date criadoEm;
    private String tipoConsulta;
}
