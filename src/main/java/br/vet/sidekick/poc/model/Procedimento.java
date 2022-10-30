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
public class Procedimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_procedimento", nullable = false)
    private Long id;

    @Column(name = "clinica", nullable = false)
    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    private Long clinica;

    @Column(name = "descricao", nullable = false)
    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    private Long descricao;

    @Column(name = "prontuario", nullable = false)
    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    private Long prontuario;

}
