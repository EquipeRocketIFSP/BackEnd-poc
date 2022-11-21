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
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Funcionario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", nullable = false)
    @Email
    private String username;

    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    @ToString.Exclude
    @Basic(fetch = FetchType.LAZY)
    private String password;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String logradouro;

    @Column(nullable = false)
    private String numero;

    @Column(nullable = false, length = 9)
    private String cep;
    private String bairro;
    private String cidade;
    private String estado;

    @CPF
    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(unique = true)
    private String rg;

    private String celular;

    @Column(nullable = true)
    private String telefone;

    @Email
    private String email = this.username;

    @ManyToOne
    private Clinica clinica;

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
