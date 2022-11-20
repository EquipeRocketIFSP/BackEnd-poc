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
public class Estoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "clinica", nullable = false)
    private Integer clinica;

    @Column(name = "entrada_qtd")
    private Long entradaQtd;

    @Column(name = "lote", nullable = false)
    private String lote;

    @Column(name = "medicamento", nullable = false)
    private Long medicamento;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @Column(name = "validade", nullable = false)
    private LocalDateTime validade;

}
