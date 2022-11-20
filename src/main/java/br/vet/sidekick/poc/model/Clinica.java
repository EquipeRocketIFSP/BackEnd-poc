package br.vet.sidekick.poc.model;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Clinica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id")
    private List<Prontuario> prontuarios;

    private String razaoSocial;

    @OneToMany(mappedBy = "id")
    private List<Telefone> telefones;

    private String logradouro;

    @CNPJ
    private String cnpj;
}
