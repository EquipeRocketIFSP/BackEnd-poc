package br.vet.sidekick.poc.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Builder
@Getter
public class CadastroAnimalDto {

    private String nome;
    private Integer idade;
    private String sexo;
    private String raca;
    private String especie;
    private String pelagem;
    private List<Long> tutores;
    private String pai;
    private String mae;

    public static CadastroAnimalDto getMock(){
        return CadastroAnimalDto.builder()
                .nome("Teste")
                .idade(1)
                .sexo("Macho")
                .raca("dgjlkdjlfg")
                .especie("dfgsdfg")
                .pelagem("dfsgsdfgsd")
                .tutores(List.of(1L))
                .build();
    }

}
