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
public class Prontuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prontuario", nullable = false)
    private Long id;

    @Column(name = "animal", nullable = false)
    private Long animal;

    @Column(name = "clinica", nullable = false)
    private Long clinica;

    @Column(name = "criado_em", nullable = false)
    private LocalDateTime criadoEm;

    @Column(name = "prontuario_retorno")
    private Long prontuarioRetorno;

    @Column(name = "veterinario", nullable = false)
    private Long veterinario;

}
