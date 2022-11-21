package br.vet.sidekick.poc.controller.dto;

import br.vet.sidekick.poc.model.Funcionario;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class CadastroFuncionarioDto {
    private Long clinica;
    private String nome;
    private String crmv;
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
    private String senha;

    public Funcionario convert() {
        return Funcionario.builder()
                .nome(this.nome)
                .cpf(this.cpf)
                .rg(this.rg)
                .cep(this.cep)
                .logradouro(this.logradouro)
                .numero(this.numero)
                .bairro(this.bairro)
                .cidade(this.cidade)
                .estado(this.estado)
                .celular(this.celular)
                .telefone(this.telefone)
                .username(this.email)
                .password(this.senha).build();
    }
}
