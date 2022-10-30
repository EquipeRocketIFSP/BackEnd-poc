package br.vet.sidekick.poc.controller.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Entity
public class Exame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exame_id", nullable = false)
    private Long id;

    @Column(name = "clinica", nullable = false)
    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    private Long clinica;

    @Column(name = "prontuario", nullable = false)
    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    private Long prontuario;

    @Column(name = "descricao")
    private String descricao;

}
