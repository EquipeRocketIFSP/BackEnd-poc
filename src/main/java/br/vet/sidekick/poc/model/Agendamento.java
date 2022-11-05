package br.vet.sidekick.poc.model;

import lombok.*;
import javax.persistence.*;
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
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "animal", nullable = false)
    private Long animal;

    @Column(name = "clinica", nullable = false)
    private Long clinica;

    @Column(name = "criadoEm", nullable = false)
    private LocalDateTime criado_em;

    @Column(name = "dataConsulta", nullable = false)
    private LocalDateTime data_consulta;

    @Column(name = "cpf", nullable = false)
    private String tipo;

}
