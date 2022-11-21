package br.vet.sidekick.poc.model;

import lombok.*;
import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Entity
public class Medicamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long clinica;
    private String nome;

    @Column(nullable = false)
    private Long codigoRegistro;


}
