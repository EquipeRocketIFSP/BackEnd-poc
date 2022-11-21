package br.vet.sidekick.poc.model;

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
    private List<Agendamento> agendamentos;

    @OneToMany(mappedBy = "id")
    private List<Tutor> tutores;

    @OneToMany(mappedBy = "id")
    private List<Procedimento> procedimentos;

    @OneToMany(mappedBy = "id")
    private List<Exame> exames;

    @OneToMany(mappedBy = "id")
    private List<Cirurgia> cirurgias;

    @OneToMany(mappedBy = "id")
    private List<Animal> animais;

    @OneToMany(mappedBy = "id")
    private List<Telefone> telefones;

    private String bairro;
    private String celular;

    @Column(nullable = false)
    private String cep;

    @Column(nullable = false)
    private String cidade;

    @NotBlank(message = "Informar o Código de Atividade do CNAE")
    private String cnae;

    @CNPJ
    @Column(nullable = false, unique = true)
    private String cnpj;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private String logradouro;

    @Column(nullable = false)
    private String numero;
    private String nomeFantasia;
    private String razaoSocial;
    private String responsavelTecnico;
    private String telefone;

    @OneToMany(mappedBy = "id")
    private List<Documento> documentos;

    @CPF
    private String donoCpf;
}
