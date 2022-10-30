package br.vet.sidekick.poc.controller.model;

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
public class Estoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estoque", nullable = false)
    private Long id;

    @Column(name = "clinica", nullable = false)
    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    private Integer clinica;

    @Column(name = "entrada_qtd")
    private Long entradaQtd;

    @Column(name = "lote", nullable = false)
    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    private String lote;

    @Column(name = "medicamento", nullable = false)
    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    private Long medicamento;

    @Column(name = "quantidade", nullable = false)
    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    private Integer quantidade;

    @Column(name = "validade", nullable = false)
    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    private LocalDateTime validade;

}
