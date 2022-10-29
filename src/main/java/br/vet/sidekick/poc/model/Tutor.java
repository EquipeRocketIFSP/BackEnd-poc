package br.vet.sidekick.poc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
//@Entity
public class Tutor {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
    private Long id;

    private String nome;
    private String rg;
    @CPF
    private String cpf;
    private String endereco;
    private String telefone;
    @Email
    private String email;

}
