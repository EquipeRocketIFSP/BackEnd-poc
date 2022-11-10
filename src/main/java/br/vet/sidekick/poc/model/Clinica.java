package br.vet.sidekick.poc.model;

import lombok.*;
import org.hibernate.validator.constraints.br.CNPJ;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Entity
public class Clinica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany(mappedBy = "clinica")
    private List<Animal> animais;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "celular")
    private String celular;

    @Column(name = "cep", nullable = false)
    private String cep;

    @Column(name = "cidade", nullable = false)
    private String cidade;

    @NotBlank(message = "Informar o Código de Atividade do CNAE")
    @Column(name = "cnae")
    private String cnae;

    @CNPJ
    @Column(name = "cnpj", nullable = false, unique = true)
    private String cnpj;

    @Email
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "estado", nullable = false)
    private String estado;

    @Column(name = "logradouro", nullable = false)
    private String logradouro;

    @Column(name = "numero", nullable = false)
    private String numero;

    @Column(name = "nome_fantasia")
    private String nomeFantasia;

    @Column(name = "razao_social")
    private String razaoSocial;

    @Column(name = "responsavel_tecnico", nullable = false)
    private String responsavelTecnico;

    @Column(name = "telefone")
    private String telefone;
}
