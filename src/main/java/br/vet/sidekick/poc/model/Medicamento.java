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
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "clinica")
    private Long clinica;

    @Column(name = "codigo_registro", nullable = false)
    private Long codigoRegistro;

    @Column(name = "nome")
    private String nome;

}
