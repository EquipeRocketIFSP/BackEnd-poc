package br.vet.sidekick.poc.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference("tutor_animal")
    private List<Animal> animais;

    @OneToMany(mappedBy = "id")
    private List<Prontuario> prontuarios;

    private String bairro;
    private String celular;
    private String cep;
    private String cidade;

    @Column(name = "clinica_id", nullable = false)
    private Long clinica;

    @CPF
    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String estado;

    @Email
    @Column(nullable = false)
    private String email;
    private String logradouro;

    @Column(nullable = false)
    private String nome;
    private String numero;
    private String rg;
    private String telefone;

}
