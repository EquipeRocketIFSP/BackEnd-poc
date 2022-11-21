package br.vet.sidekick.poc.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Setter
@SuperBuilder
public class Veterinario extends Funcionario {
    @Column(name = "registro_crmv", nullable = false)
    private String registroCRMV;

    public Veterinario(Funcionario funcionario) {
        super();
        this.setUsername(funcionario.getUsername());
        this.setPassword(funcionario.getPassword());
        this.setNome(funcionario.getNome());
        this.setClinica(funcionario.getClinica());
        this.setLogradouro(funcionario.getLogradouro());
        this.setNumero(funcionario.getNumero());
        this.setCep(funcionario.getCep());
        this.setBairro(funcionario.getBairro());
        this.setCidade(funcionario.getCidade());
        this.setEstado(funcionario.getEstado());
        this.setCpf(funcionario.getCpf());
        this.setRg(funcionario.getRg());
        this.setCelular(funcionario.getCelular());
        this.setTelefone(funcionario.getTelefone());
        this.setEmail(funcionario.getEmail());
    }
}
