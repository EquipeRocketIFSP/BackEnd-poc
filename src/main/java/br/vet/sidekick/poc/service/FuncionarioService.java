package br.vet.sidekick.poc.service;

import br.vet.sidekick.poc.model.Funcionario;

public interface FuncionarioService {
    Boolean authenticate(String username, String password);

    Funcionario get(Funcionario funcionario);
}
