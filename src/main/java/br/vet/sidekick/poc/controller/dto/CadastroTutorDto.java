package br.vet.sidekick.poc.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CadastroTutorDto {
    @JsonProperty("nome")
    private String nome;

    @JsonProperty("cpf")
    private String cpf;

    @JsonProperty("rg")
    private String rg;

    @JsonProperty("cep")
    private String cep;

    @JsonProperty("logradouro")
    private String logradouro;

    @JsonProperty("numero")
    private String numero;

    @JsonProperty("bairro")
    private String bairro;

    @JsonProperty("cidade")
    private String cidade;

    @JsonProperty("estado")
    private String estado;

    @JsonProperty("celular")
    private String celular;

    @JsonProperty("telefone")
    private String telefone;

    public static CadastroTutorDto getMock(){
        return CadastroTutorDto.builder()
                .nome("Caique Daniel Freitas Eufrásio da Silva")
                .cpf("465.465.465-46")
                .rg("456546")
                .cep("03808-130")
                .logradouro("Rua Miguel Rachid")
                .numero("55465")
                .bairro("Vila Paranaguá")
                .cidade("São Paulo")
                .estado("AL")
                .celular("(45) 64654-6546")
                .telefone("(11) 9858-1458")
                .build();
    }
}
