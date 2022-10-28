package br.vet.sidekick.poc.model;


import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
<<<<<<< HEAD
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
=======
import java.io.Serializable;
>>>>>>> autenticacaojwt

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Entity
<<<<<<< HEAD
public class Funcionario implements UserDetails {

    private static final long serialVersionUID = 1L;
=======
public class Funcionario implements Serializable {
>>>>>>> autenticacaojwt

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

<<<<<<< HEAD
=======
    @Getter
>>>>>>> autenticacaojwt
    @Column(name = "user_name", nullable = false)
    @Email
    private String username;

    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    @ToString.Exclude
    @Basic(fetch = FetchType.LAZY)
    private String password;

    @Email
    private String email = this.username;
<<<<<<< HEAD

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "funcionario")
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
=======
>>>>>>> autenticacaojwt

}
