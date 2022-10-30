package br.vet.sidekick.poc.model;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.br.CNPJ;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Entity
public class Clinica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_clinica", nullable = false)
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

    @CNPJ
    @Column(name = "cnpj", nullable = false, unique = true)
    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    private String cnpj;

    @Email
    @Column(name = "email", nullable = false, unique = true)
    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    private String email;

    @Column(name = "estado", nullable = false)
    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    private String estado;

    @Column(name = "logradouro", nullable = false)
    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    private String logradouro;

    @Column(name = "numero", nullable = false)
    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    private Integer numero;

    @Column(name = "razao_social", nullable = false)
    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    private String razaoSocial;

    @Column(name = "responsavel_tecnico", nullable = false, unique = true)
    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    private String responsavelTecnico;

    @Column(name = "telefone")
    private String telefone;

}
