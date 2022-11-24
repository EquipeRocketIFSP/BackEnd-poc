package br.vet.sidekick.poc.service;

import br.vet.sidekick.poc.controller.dto.CadastroClinicaDto;
import br.vet.sidekick.poc.model.Clinica;
import br.vet.sidekick.poc.model.Funcionario;
import java.util.Optional;

public interface FuncionarioService {
    Boolean authenticate(String username, String password);

    Funcionario get(Funcionario funcionario);

    Optional<Funcionario> create(Funcionario funcionario);

    Funcionario save(Funcionario funcionario);

    void deleteById(Long id);

    Funcionario createDonoClinica(CadastroClinicaDto cadastro, Clinica clinica) throws RuntimeException;

    Funcionario find(Long funcionarioId);
}
