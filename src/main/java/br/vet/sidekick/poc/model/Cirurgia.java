package br.vet.sidekick.poc.model;

import lombok.*;
import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Entity
public class Cirurgia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "categoria_paciente", nullable = false)
    private String categoriaPaciente;

    @Column(name = "clinica", nullable = false)
    private Long clinica;

    @Column(name = "prontuario", nullable = false, unique = true)
    private Long prontuario;

    @Column(name = "tipo_cirurgia", nullable = false)
    private Long tipoCirurgia;
}
