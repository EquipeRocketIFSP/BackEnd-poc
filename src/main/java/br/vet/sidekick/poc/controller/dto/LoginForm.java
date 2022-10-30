package br.vet.sidekick.poc.controller.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.Email;

@Getter
@Setter
public class LoginForm {

    @Email
    String email;
    String password;

    public UsernamePasswordAuthenticationToken convert() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}
