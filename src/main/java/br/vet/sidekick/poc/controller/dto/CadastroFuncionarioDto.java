package br.vet.sidekick.poc.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CadastroFuncionarioDto {
    @JsonProperty("funcionario-nome")
    private String funcionarioNome;

    @JsonProperty("funcionario-crmv")
    private String funcionarioCrmv;

    @JsonProperty("funcionario-cpf")
    private String funcionarioCpf;

    @JsonProperty("funcionario-rg")
    private String funcionarioRg;

    @JsonProperty("funcionario-cep")
    private String funcionarioCep;

    @JsonProperty("funcionario-logradouro")
    private String funcionarioLogradouro;

    @JsonProperty("funcionario-numero")
    private String funcionarioNumero;

    @JsonProperty("funcionario-bairro")
    private String funcionarioBairro;

    @JsonProperty("funcionario-cidade")
    private String funcionarioCidade;

    @JsonProperty("funcionario-estado")
    private String funcionarioEstado;

    @JsonProperty("funcionario-celular")
    private String funcionarioCelular;

    @JsonProperty("funcionario-telefone")
    private String funcionarioTelefone;

    @JsonProperty("funcionario-email")
    private String funcionarioEmail;

    @JsonProperty("funcionario-senha")
    private String funcionarioSenha;

}
