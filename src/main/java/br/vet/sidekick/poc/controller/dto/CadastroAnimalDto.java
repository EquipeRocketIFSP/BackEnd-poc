package br.vet.sidekick.poc.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CadastroAnimalDto {

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("idade")
    private String idade;

    @JsonProperty("sexo")
    private String sexo;

    @JsonProperty("raca")
    private String raca;

    @JsonProperty("especie")
    private String especie;

    @JsonProperty("pelagem")
    private String pelagem;

    @JsonProperty("tutor")
    private Long tutor;

    @JsonProperty("pai")
    private Long pai;

    @JsonProperty("mae")
    private Long mae;

}
