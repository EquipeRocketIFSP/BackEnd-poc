package br.vet.sidekick.poc.controller.model;

import lombok.*;
import org.hibernate.validator.constraints.br.CPF;
import javax.persistence.*;
import javax.validation.constraints.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Entity
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tutor", nullable = false)
    private Long id;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "celular")
    private String celular;

    @Column(name = "cep", nullable = false)
    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    private String cep;

    @Column(name = "cidade", nullable = false)
    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    private String cidade;

    @Column(name = "clinica", nullable = false)
    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    private Long clinica;

    @CPF
    @Column(name = "cpf", nullable = false, unique = true)
    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    private String cpf;

    @Column(name = "estado", nullable = false)
    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    private String estado;

    @Email
    @Column(name = "email", nullable = false)
    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    private String email;

    @Column(name = "logradouro")
    private String logradouro;

    @Column(name = "nome", nullable = false)
    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    private String nome;

    @Column(name = "numero", nullable = false)
    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    private Integer numero;

    @Column(name = "rg")
    private String rg;

    @Column(name = "telefone")
    private String telefone;

}
