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
public class Cirurgia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cirurgia", nullable = false)
    private Long id;

    @Column(name = "categoria_paciente", nullable = false)
    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    private String categoriaPaciente;

    @Column(name = "clinica", nullable = false)
    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    private Long clinica;

    @Column(name = "prontuario", nullable = false, unique = true)
    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    private Long prontuario;

    @Column(name = "tipo_cirurgia", nullable = false)
    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    private Long tipoCirurgia;

}
