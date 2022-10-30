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
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_agendamento", nullable = false)
    private Long id;

    @Column(name = "animal", nullable = false)
    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    private Long animal;

    @Column(name = "clinica", nullable = false)
    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    private Long clinica;

    @Column(name = "criadoEm", nullable = false)
    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    private LocalDateTime criado_em;

    @Column(name = "dataConsulta", nullable = false)
    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    private LocalDateTime data_consulta;

    @Column(name = "cpf", nullable = false)
    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    private String tipo;

}
