package br.vet.sidekick.poc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nome;
    private String rg;
    @CPF
    private String cpf;
    private String endereco;

//    @OneToMany(mappedBy = "id")
//    private List<Telefone> telefones;
    private String telefone;
    @Email
    private String email;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id")
    private List<Prontuario> prontuarios;
}
