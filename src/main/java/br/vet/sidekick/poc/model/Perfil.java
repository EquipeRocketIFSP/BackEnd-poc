package br.vet.sidekick.poc.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "perfil")
@Getter
@Setter
public class Perfil implements GrantedAuthority{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    @JsonBackReference("funcionario_perfis")
    private Funcionario funcionario;

    @Override
    public String getAuthority() {
        return null;
    }
}