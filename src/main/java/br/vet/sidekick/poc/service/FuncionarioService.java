package br.vet.sidekick.poc.service;

import br.vet.sidekick.poc.controller.model.Funcionario;

import java.util.Optional;

public interface FuncionarioService {
    Boolean authenticate(String username, String password);

    Funcionario get(Funcionario funcionario);

    Optional<Funcionario> create(Funcionario funcionario);
}
