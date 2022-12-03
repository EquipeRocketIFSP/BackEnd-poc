package br.vet.sidekick.poc.controller.dto;

import br.vet.sidekick.poc.model.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@ToString
public class ProntuarioDto {
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
    private int quantidade;
    private String certvetCode;

    public Prontuario convert() {
        return Prontuario.builder()
                .diagnostico(this.diagnostico)
                .observacoes(this.observacoes)
                .medicamento(this.medicamento)
                .medida(this.medida)
                .prescricoes(this.prescricoes)
                .quantidade(this.quantidade)
                .build();
    }

    public String getCertvetCode() {
        return certvetCode;
    }
}
