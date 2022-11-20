package br.vet.sidekick.poc.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private String tipoDocumento;
    private LocalDateTime dateCreation;
    private Integer versao;

    @ManyToOne(optional = false)
    @JoinColumn(name = "prontuario_id", nullable = false)
    @ToString.Exclude
    private Prontuario prontuario;

}
