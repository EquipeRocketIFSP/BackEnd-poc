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

    private String bairro;

    @Column(name = "celular")
    private String celular;

    @Column(name = "cep")
    private String cep;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "clinica_id", nullable = false)
    private Long clinica;

    @CPF
    @Column(name = "cpf", nullable = false)
    private String cpf;

    @Column(name = "estado", nullable = false)
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

    @Column(name = "telefone")
    private String telefone;

}
