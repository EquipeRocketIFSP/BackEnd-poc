package br.vet.sidekick.poc.controller.dto;

import br.vet.sidekick.poc.model.Funcionario;
import br.vet.sidekick.poc.model.Veterinario;
import lombok.Getter;

import java.util.Optional;

@Getter
public class RecuperarFuncionarioDto {
    private final Long id;
    private final String email, nome, logradouro, numero, cep, bairro, estado, cpf, rg, celular, cidade;
    private final Optional<String> crmv, telefone;

    public RecuperarFuncionarioDto(Funcionario data) {
        this.id = data.getId();
        this.email = data.getUsername();
        this.nome = data.getNome();
        this.logradouro = data.getLogradouro();
        this.numero = data.getNumero();
        this.cep = data.getCep();
        this.bairro = data.getBairro();
        this.estado = data.getEstado();
        this.cpf = data.getCpf();
        this.rg = data.getRg();
        this.celular = data.getCelular();
        this.telefone = Optional.ofNullable(data.getTelefone());
        this.cidade = data.getCidade();
        this.crmv = Optional.empty();
    }

    public RecuperarFuncionarioDto(Veterinario data) {
        this.id = data.getId();
        this.email = data.getUsername();
        this.nome = data.getNome();
        this.logradouro = data.getLogradouro();
        this.numero = data.getNumero();
        this.cep = data.getCep();
        this.bairro = data.getBairro();
        this.estado = data.getEstado();
        this.cpf = data.getCpf();
        this.rg = data.getRg();
        this.celular = data.getCelular();
        this.telefone = Optional.ofNullable(data.getTelefone());
        this.cidade = data.getCidade();
        this.crmv = Optional.of(data.getRegistroCRMV());
    }
}
