package br.vet.sidekick.poc.controller.dto;

import br.vet.sidekick.poc.model.Funcionario;
import br.vet.sidekick.poc.model.Veterinario;
import lombok.Getter;

import java.util.Optional;

@Getter
public class RecuperarFuncionarioDto {
    private Long id;
    private String email, nome, logradouro, numero, cep, bairro, estado, cpf, rg, celular, telofone;
    private Optional<String> crmv;

    public RecuperarFuncionarioDto(Funcionario funcionario) {
        this.id = funcionario.getId();
        this.email = funcionario.getUsername();
        this.nome = funcionario.getNome();
        this.logradouro = funcionario.getLogradouro();
        this.numero = funcionario.getNumero();
        this.cep = funcionario.getCep();
        this.bairro = funcionario.getBairro();
        this.estado = funcionario.getEstado();
        this.cpf = funcionario.getCpf();
        this.rg = funcionario.getRg();
        this.celular = funcionario.getCelular();
        this.telofone = funcionario.getTelefone();
        this.crmv = Optional.empty();
    }

    public RecuperarFuncionarioDto(Veterinario veterinario) {
        this.id = veterinario.getId();
        this.email = veterinario.getUsername();
        this.nome = veterinario.getNome();
        this.logradouro = veterinario.getLogradouro();
        this.numero = veterinario.getNumero();
        this.cep = veterinario.getCep();
        this.bairro = veterinario.getBairro();
        this.estado = veterinario.getEstado();
        this.cpf = veterinario.getCpf();
        this.rg = veterinario.getRg();
        this.celular = veterinario.getCelular();
        this.telofone = veterinario.getTelefone();
        this.crmv = Optional.of(veterinario.getRegistroCRMV());
    }
}
