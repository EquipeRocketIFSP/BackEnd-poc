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
    private String tutor;

    @JsonProperty("pai")
    private String pai;

    @JsonProperty("mae")
    private String mae;

    public static CadastroAnimalDto getMock(){
        return CadastroAnimalDto.builder()
                .nome("Teste")
                .idade("55")
                .sexo("Macho")
                .raca("dgjlkdjlfg")
                .especie("dfgsdfg")
                .pelagem("dfsgsdfgsd")
                .tutor("Tutor")
                .build();
    }

}
