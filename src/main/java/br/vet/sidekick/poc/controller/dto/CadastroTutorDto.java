package br.vet.sidekick.poc.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class CadastroTutorDto {

    private String nome;
    private String cpf;
    private String rg;
    private String cep;
    private String logradouro;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String celular;
    private String telefone;
    private String email;
    private long clinica;

}
