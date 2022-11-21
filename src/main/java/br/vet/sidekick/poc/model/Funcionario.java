package br.vet.sidekick.poc.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Table(name = "funcionario")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Funcionario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_name", nullable = false)
    @Email
    private String username;

    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    @ToString.Exclude
    @Basic(fetch = FetchType.LAZY)
    private String password;

    @Column(name = "nome", nullable = false)
    private String nome;


    @ManyToOne
    @JoinColumn(name = "clinica")
    private Clinica clinica;

    @Column(name = "logradouro", nullable = false)
    private String logradouro;

    @Column(name = "numero", nullable = false)
    private String numero;

    @Column(name = "cep", nullable = false, length = 9)
    private String cep;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "estado")
    private String estado;

    @CPF
    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @Column(name = "rg", unique = true)
    private String rg;

    @Column(name = "celular")
    private String celular;

    @Column(name = "telefone", nullable = true)
    private String telefone;

    @Email
    private String email = this.username;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "funcionario")
    private List<Perfil> perfis = new ArrayList<>();

    public void setPerfis(List<Perfil> perfis) {
        this.perfis = perfis;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
