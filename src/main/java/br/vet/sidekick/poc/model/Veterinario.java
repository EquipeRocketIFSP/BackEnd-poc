package br.vet.sidekick.poc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@SuperBuilder
public class Veterinario extends Funcionario {
    @Column(nullable = false)
    private String registroCRMV;

    @OneToMany(mappedBy = "id")
    private List<Prontuario> prontuarios;

    @OneToMany
    private List<Documento> documentos;

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
