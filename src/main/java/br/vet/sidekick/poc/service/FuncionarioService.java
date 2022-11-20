package br.vet.sidekick.poc.service;

import br.vet.sidekick.poc.model.Funcionario;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface FuncionarioService {


    Optional<Funcionario> create(Funcionario funcionario);

    Optional<Funcionario> findById(long parseLong);
}
