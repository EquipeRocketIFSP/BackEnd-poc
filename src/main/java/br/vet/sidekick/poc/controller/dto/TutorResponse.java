package br.vet.sidekick.poc.controller.dto;

import br.vet.sidekick.poc.model.Tutor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Column;
import javax.validation.constraints.Email;

public class TutorResponse {

    private Long id;
    private String bairro;
    private String celular;
    private String cep;
    private String cidade;
    private Long clinica;
    private String cpf;
    private String estado;
    private String email;
    private String logradouro;
    private String nome;
    private String numero;
    private String rg;
    private String telefone;

    public TutorResponse(Tutor tutor) {
        this.id = tutor.getId();
        this.bairro = tutor.getBairro();
        this.celular = tutor.getCelular();
        this.cep = tutor.getCep();
        this.cidade = tutor.getCidade();
        this.clinica = tutor.getClinica();
        this.cpf = tutor.getCpf();
        this.estado = tutor.getEstado();
        this.email = tutor.getEmail();
        this.logradouro = tutor.getLogradouro();
        this.nome = tutor.getNome();
        this.numero = tutor.getNumero();
        this.rg = tutor.getRg();
        this.telefone = tutor.getTelefone();
    }
}
