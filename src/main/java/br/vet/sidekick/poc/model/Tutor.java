package br.vet.sidekick.poc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToMany
    private List<Animal> animais;

    private String bairro;

    @Column(name = "celular")
    private String celular;

    @Column(name = "cep", nullable = false)
    private String cep;

    @Column(name = "cidade", nullable = false)
    private String cidade;

    @Column(name = "clinica", nullable = false)
    private Long clinica;

    @CPF
    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @Column(name = "estado", nullable = false)
    private String estado;

    @Email
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "logradouro")
    private String logradouro;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "numero", nullable = false)
    private Integer numero;

    @Column(name = "rg")
    private String rg;

    @Column(name = "telefone")
    private String telefone;

}
