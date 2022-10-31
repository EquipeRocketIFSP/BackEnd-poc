package br.vet.sidekick.poc.model;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Entity
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_animal", nullable = false)
    private Long id;

    @Column(name = "clinica", nullable = false)
    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    private Long clinica;

    @Column(name = "especie", nullable = false)
    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    private String especie;

    @Column(name = "forma_identificacao", nullable = false)
    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    private String formaIdentificacao;

    @Column(name = "idade")
    private Integer idade;

    @Column(name = "mae")
    private Long mae;

    @Column(name = "nome")
    private String nome;

    @Column(name = "pai")
    private Long pai;

    @Column(name = "pelagem")
    private String pelagem;

    @Column(name = "raca", nullable = false)
    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    private String raca;

    @Column(name = "sexo", nullable = false)
    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    private String sexo;

    @Column(name = "tutor", nullable = false)
    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    private Long tutor;

}
