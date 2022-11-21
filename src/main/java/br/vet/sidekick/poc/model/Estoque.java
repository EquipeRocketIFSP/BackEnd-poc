package br.vet.sidekick.poc.model;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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

    @Column(nullable = false)
    @OneToMany
    private List<Clinica> clinicas;

    private Long entradaQtd;

    @Column(nullable = false)
    private String lote;

    @Column(nullable = false)
    private Long medicamento;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private LocalDateTime validade;

}
