package br.vet.sidekick.poc.conf.security.service;

import br.vet.sidekick.poc.model.Funcionario;
import br.vet.sidekick.poc.repository.FuncionarioRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationService implements UserDetailsService {
    private static String NOT_AUTHORIZED = "Credenciais inválidas";

    @Autowired
    private FuncionarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(NOT_AUTHORIZED));
    }


}
