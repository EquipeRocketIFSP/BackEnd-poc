package br.vet.sidekick.poc.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProcedimentoDto {
    private String tipoProcedimento;
    private String tipo;
    private String descricao;
}
