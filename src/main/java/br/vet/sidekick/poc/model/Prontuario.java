package br.vet.sidekick.poc.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Entity
public class Prontuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prontuario", nullable = false)
    private Long id;

    @Column(name = "animal", nullable = false)
    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    private Long animal;

    @Column(name = "clinica", nullable = false)
    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    private Long clinica;

    @Column(name = "criado_em", nullable = false)
    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    private LocalDateTime criadoEm;

    @Column(name = "prontuario_retorno")
    private Long prontuarioRetorno;

    @Column(name = "veterinario", nullable = false)
    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    private Long veterinario;

}
