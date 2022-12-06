package br.vet.sidekick.poc.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Clinica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "id")
    @JsonBackReference("clinica_documentos")
    @ToString.Exclude
    private List<Documento> documentos;

    @OneToMany(mappedBy = "id")
    @ToString.Exclude
    @JsonBackReference("clinica_animais")
    private List<Animal> animais;

    @Column(nullable = false)
    private String cep;

    @Column(nullable = false)
    private String cidade;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private String logradouro;

    @Column(nullable = false)
    private String numero;

    @CPF
    private String donoCpf;

    @NotBlank(message = "Informar o Código de Atividade do CNAE")
    private String cnae;

    @CNPJ
    @Column(nullable = false, unique = true)
    private String cnpj;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    private String bairro;
    private String celular;
    private String nomeFantasia;
    private String razaoSocial;
    private String responsavelTecnico;
    private String telefone;
}
