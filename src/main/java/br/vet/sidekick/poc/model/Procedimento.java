package br.vet.sidekick.poc.model;

import lombok.*;
import javax.persistence.*;

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
    private Long clinica;

    @Column(name = "descricao", nullable = false)
    private Long descricao;

    @Column(name = "prontuario", nullable = false)
    private Long prontuario;

}
