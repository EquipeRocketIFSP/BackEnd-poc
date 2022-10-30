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
public class Veterinario extends Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_veterinario", nullable = false)
    private Long id;

    @Column(name = "clinica")
    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    private Long clinica;

    @Column(name = "crmv", nullable = false, unique = true)
    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    private String crmv;

}
