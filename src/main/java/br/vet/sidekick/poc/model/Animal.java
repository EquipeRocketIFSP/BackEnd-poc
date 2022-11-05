package br.vet.sidekick.poc.model;

import lombok.*;

import javax.persistence.*;

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

    @Column(name = "clinica", nullable = false)
    private Long clinica;

    @Column(name = "especie", nullable = false)
    private String especie;

    @Column(name = "forma_identificacao", nullable = false)
    private String formaIdentificacao;

    @Column(name = "idade")
    private Integer idade;

    @Column(name = "mae")
    private Long mae;

    @Column(name = "nome")
    private String nome;

    @Column(name = "pai")
    private Long pai;

    @Column(name = "pelagem")
    private String pelagem;

    @Column(name = "raca", nullable = false)
    private String raca;

    @Column(name = "sexo", nullable = false)
    private String sexo;

    @Column(name = "tutor", nullable = false)
    private Long tutor;

    private String outros;

}
