package br.vet.sidekick.poc.service;

import br.vet.sidekick.poc.controller.dto.CadastroClinicaDto;
import br.vet.sidekick.poc.model.Clinica;
import br.vet.sidekick.poc.model.Funcionario;
import org.springframework.stereotype.Service;
import java.util.Optional;
@Service

public interface FuncionarioService {
    Boolean authenticate(String username, String password);

    Funcionario get(Funcionario funcionario);

    Optional<Funcionario> create(Funcionario funcionario);

    Optional<Funcionario> findById(long parseLong);
    Funcionario save(Funcionario funcionario);

    void deleteById(Long id);

    Funcionario createDonoClinica(CadastroClinicaDto cadastro, Clinica clinica) throws RuntimeException;
}
