package br.vet.sidekick.poc.service;

import br.vet.sidekick.poc.controller.dto.CadastroFuncionarioDto;
import br.vet.sidekick.poc.model.Funcionario;

import java.util.Optional;

public interface FuncionarioService {
    Boolean authenticate(String username, String password);

    Funcionario get(Funcionario funcionario);

    Optional<Funcionario> create(Funcionario funcionario);
//    void create(CadastroFuncionarioDto cadastroFuncionarioDto);
}
