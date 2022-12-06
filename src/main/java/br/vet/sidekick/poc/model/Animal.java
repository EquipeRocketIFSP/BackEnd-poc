package br.vet.sidekick.poc.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    private Long id;

    @JoinColumn(name = "clinica_id", nullable = false)
    @ManyToOne
    @JsonBackReference("clinica_animais")
    private Clinica clinica;

    @Column(nullable = false)
    private String especie;

    @OneToMany(mappedBy = "id")
    @Column(name = "filho_id")
//    @JsonManagedReference()
    @JsonIgnore
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

//    @Pattern(message = "O sexo do animal deve ser: MASCULINO, FEMININO ou INDEFINIDO", regexp = "(MASCULINO|FEMININO|INDEFINIDO)")
    @Column(name = "sexo", nullable = false)
    private String sexo;

    @Setter
    @ManyToMany
//    @Column(name = "tutor_id")
    @JsonBackReference("tutor_animal")
    @JoinTable(name = "tutor_animal",
            joinColumns = {@JoinColumn(name = "animal_id")},
            inverseJoinColumns = {@JoinColumn(name = "tutor_id")}
    )
    private List<Tutor> tutores;

    @OneToMany(mappedBy = "id")
    @JsonManagedReference("prontuario_animais")
    private List<Prontuario> prontuarios;

}
