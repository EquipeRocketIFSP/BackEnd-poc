package br.vet.sidekick.poc.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
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
    @Column(name = "id")
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "clinica_id", nullable = false)
    @Column(name = "clinica_id", nullable = false)
    private Long clinica;

    @Column(name = "especie", nullable = false)
    private String especie;

    @OneToMany(mappedBy = "id")
    @Column(name = "filho_id")
    private List<Animal> filho;

    @Column(name = "forma_identificacao")
    private String formaIdentificacao;

    @PositiveOrZero(message = "Idade tem que ser maior ou igual a zero ano(s)")
    @Column(name = "idade")
    private Integer idade;

    @ManyToOne
    @JoinColumn(name = "mae_id", insertable = false, updatable = false)
    private Animal mae;

    @Column(name = "nome")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "pai_id", insertable = false, updatable = false)
    private Animal pai;

    @Column(name = "pelagem")
    private String pelagem;

    @NotBlank(message = "É necessário informar a raça do animal")
    @Column(name = "raca", nullable = false)
    private String raca;

    @Pattern(message = "O sexo do animal deve ser: MASCULINO, FEMININO ou INDEFINIDO", regexp = "(MASCULINO|FEMININO|INDEFINIDO)")
    @Column(name = "sexo", nullable = false)
    private String sexo;

//    @ManyToMany
    @Column(name = "tutor_id", nullable = false)
    private Long tutor;

}
