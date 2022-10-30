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
public class Medicamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medicamento_id", nullable = false)
    private Long id;

    @Column(name = "clinica")
    private Long clinica;

    @Column(name = "codigo_registro", nullable = false)
    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    private Long codigoRegistro;

    @Column(name = "nome")
    private String nome;

}
