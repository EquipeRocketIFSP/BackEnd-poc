package br.vet.sidekick.poc.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CadastroFuncionarioDto {

    @JsonProperty("funcionario-nome")
    private String nome;

    @JsonProperty("funcionario-crmv")
    private String crmv;

    @JsonProperty("funcionario-cpf")
    private String cpf;

    @JsonProperty("funcionario-rg")
    private String rg;

    @JsonProperty("funcionario-cep")
    private String cep;

    @JsonProperty("funcionario-logradouro")
    private String logradouro;

    @JsonProperty("funcionario-numero")
    private String numero;

    @JsonProperty("funcionario-bairro")
    private String bairro;

    @JsonProperty("funcionario-cidade")
    private String cidade;

    @JsonProperty("funcionario-estado")
    private String estado;

    @JsonProperty("funcionario-celular")
    private String celular;

    @JsonProperty("funcionario-telefone")
    private String telefone;

    @JsonProperty("funcionario-email")
    private String email;

    @JsonProperty("funcionario-senha")
    private String senha;


    public static CadastroFuncionarioDto getMock(){
        return CadastroFuncionarioDto.builder()
                .nome("Caique Daniel Freitas Eufrásio da Silva")
                .crmv("465465")
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
                .email("caiquedaniel8@gmail.com")
                .senha("123456")
                .build();
    }
}
