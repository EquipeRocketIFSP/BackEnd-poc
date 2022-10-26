package br.vet.sidekick.poc.model;


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

    private String nome;

    private Long clinica;

    private String logradouro;

    private Integer numero;

    private String cep;

    private String bairro;

    private String cidade;

    private String estado;

    private String cpf;

    private String rg;

    private String celular;

    @Email
    private String email = this.username;
    public String getUsername() {
        return this.username;
    }

}
