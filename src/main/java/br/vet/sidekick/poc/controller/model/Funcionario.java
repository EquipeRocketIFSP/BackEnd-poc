package br.vet.sidekick.poc.controller.model;


import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Entity
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Getter(AccessLevel.NONE)
    @Column(name = "user_name", nullable = false)
    @Email
    private String username;

    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    @ToString.Exclude
    @Basic(fetch = FetchType.LAZY)
    private String password;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "clinica", nullable = false)
    private Long clinica;

    @Column(name = "logradouro", nullable = false)
    private String logradouro;

    @Column(name = "numero", nullable = false)
    private Integer numero;

    @Column(name = "cep", nullable = false)
    private String cep;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "estado")
    private String estado;

    @Column(name = "cpf", nullable = false, unique = true)
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
