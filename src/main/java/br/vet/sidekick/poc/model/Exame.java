package br.vet.sidekick.poc.model;

import lombok.*;
import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Entity
public class Exame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "clinica", nullable = false)
    private Long clinica;

    @Column(name = "prontuario", nullable = false)
    private Long prontuario;

    @Column(name = "descricao")
    private String descricao;

}
