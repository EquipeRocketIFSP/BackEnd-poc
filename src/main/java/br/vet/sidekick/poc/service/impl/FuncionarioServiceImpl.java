package br.vet.sidekick.poc.service.impl;

import br.vet.sidekick.poc.model.Funcionario;
import br.vet.sidekick.poc.repository.FuncionarioRepository;
import br.vet.sidekick.poc.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Optional;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Override
    public Boolean authenticate(String username, String password) {
        return funcionarioRepository.existsByUsernameAndPassword(username, password);
    }

    @Override
    public Funcionario get(Funcionario funcionario) {
        return funcionarioRepository.getByUsername(funcionario.getUsername());
    }

    @Override
    public Optional<Funcionario> create(Funcionario funcionario) {
        return funcionarioRepository.existsByUsername(funcionario.getUsername())
                ? Optional.empty()
                : Optional.of(funcionarioRepository.save(funcionario));
    }
}