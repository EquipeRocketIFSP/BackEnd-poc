package br.vet.sidekick.poc.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id")
    private List<Tutor> tutores;
    private String nome;
    private String especie;
    private String raca;
    private String sexo;
    private Integer idade;
    private String pelagem;
    private String outros;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id")
    private List<Prontuario> prontuarios;
}
