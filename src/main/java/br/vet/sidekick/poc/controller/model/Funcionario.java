package br.vet.sidekick.poc.controller.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Entity
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "funcionario_id", nullable = false)
    private Long id;

    @Getter(AccessLevel.NONE)
    @Column(name = "user_name", nullable = false)
    @Email
    private String username;

    @ToString.Exclude
    @Basic(fetch = FetchType.LAZY)
    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    private String password;

    @Column(name = "nome", nullable = false)
    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    private String nome;

    @Column(name = "clinica", nullable = false)
    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    private Long clinica;

    @Column(name = "logradouro", nullable = false)
    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    private String logradouro;

    @Column(name = "numero", nullable = false)
    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    private Integer numero;

    @Column(name = "cep", nullable = false)
    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    private String cep;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "estado")
    private String estado;

    @CPF
    @Column(name = "cpf", nullable = false, unique = true)
    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    private String cpf;

    @Column(name = "rg", unique = true)
    private String rg;

    @Column(name = "celular")
    private String celular;

    @Email
    private String email = this.username;
    public String getUsername() {
        return this.username;
    }

}
