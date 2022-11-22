package br.vet.sidekick.poc.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference("veterinario_prontuarios")
    private List<Prontuario> prontuarios;

    @OneToMany
    @JsonManagedReference("veterinario_documentos")
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
    public String getUsername(){
        return super.getUsername();
    }
    public String getPassword(){
        return super.getPassword();
    }
    public String getNome(){
        return super.getNome();
    }
    public Clinica getClinica(){
        return super.getClinica();
    }
    public String getLogradouro(){
        return super.getLogradouro();
    }
    public String getNumero(){
        return super.getNumero();
    }
    public String getCep(){
        return super.getCep();
    }
    public String getBairro(){
        return super.getBairro();
    }
    public String getCidade(){
        return super.getCidade();
    }
    public String getEstado(){
        return super.getEstado();
    }
    public String getCpf(){
        return super.getCpf();
    }
    public String getRg(){
        return super.getRg();
    }
    public String getCelular(){
        return super.getCelular();
    }
    public String getTelefone(){
        return super.getTelefone();
    }
    public String getEmail(){
        return super.getEmail();
    }
}
