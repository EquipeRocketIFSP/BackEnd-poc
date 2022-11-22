package br.vet.sidekick.poc.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "id")
    @Setter
    @JsonManagedReference("animal_prontuarios")
    private List<Prontuario> prontuarios;

    @OneToMany(mappedBy = "id")
    @JsonManagedReference("animal_agendamentos")
    private List<Agendamento> agendamentos;

    private String nome;
    private String outros;
    private String pelagem;

    @NotBlank(message = "É necessário informar a raça do animal")
    @Column(nullable = false)
    private String raca;

    @Pattern(message = "O sexo do animal deve ser: MASCULINO, FEMININO ou INDEFINIDO", regexp = "(MASCULINO|FEMININO|INDEFINIDO)")
    @Column(nullable = false)
    private String sexo;


    @Column(nullable = false)
    private String especie;

    @ManyToOne
    @JsonBackReference("clinica_animais")
    private Clinica clinica;

//    @OneToMany(mappedBy = "id")
//    @Column(name = "filho_id")
//    @JsonManagedReference("animal_filhos")
//    private List<Animal> filho;

    @Column(name = "forma_identificacao")
    private String formaIdentificacao;

    @PositiveOrZero(message = "Idade tem que ser maior ou igual a zero ano(s)")
    private Integer idade;

//    @ManyToOne
//    @JoinColumn(name = "mae_id", insertable = false, updatable = false)
//    private Animal mae;
//
//    @ManyToOne
//    @JoinColumn(name = "pai_id", insertable = false, updatable = false)
//    private Animal pai;
    @Setter
    @ManyToMany
    @JoinTable(
            name = "tutor_animal",
            joinColumns = {@JoinColumn(name = "animal_id")},
            inverseJoinColumns = {@JoinColumn(name = "tutor_id")}
    )
    private List<Tutor> tutores;
}
