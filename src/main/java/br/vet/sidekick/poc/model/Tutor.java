package br.vet.sidekick.poc.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;
import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "tutores")
//    @Column(name = "animal_id")
//    @JsonManagedReference(value = "tutor_animal")

    private List<Animal> animais;

    private String bairro;

    private String celular;

    private String cep;

    private String cidade;

    @ManyToOne
    @JsonBackReference("clinica_tutores")
    private Clinica clinica;

    @CPF
    @Column(nullable = false)
    private String cpf;
    private String endereco;

//    @OneToMany(mappedBy = "id")
//    private List<Telefone> telefones;
    private String telefone;

    @Column(nullable = false)
    private String estado;

    @Email
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "logradouro")
    private String logradouro;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "numero")
    private String numero;

    @Column(name = "rg")
    private String rg;

}
