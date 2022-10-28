package br.vet.sidekick.poc.model;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
//@Entity
public class Animal {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
    private Long id;

    private String nome;
    private String especie;
    private String raca;
    private String sexo;
    private Integer idade;
    private String pelagem;
    private String outros;

}
