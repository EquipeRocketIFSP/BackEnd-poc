package br.vet.sidekick.poc.service;

import br.vet.sidekick.poc.controller.dto.CadastroClinicaDto;
import br.vet.sidekick.poc.controller.dto.VeterinarioDto;
import br.vet.sidekick.poc.exceptionResolver.exception.FuncionarioAlreadyExistsException;
import br.vet.sidekick.poc.model.Clinica;
import br.vet.sidekick.poc.model.Funcionario;
import br.vet.sidekick.poc.model.Veterinario;

import java.util.Optional;

public interface VeterinarioService {

    Optional<Veterinario> findByEmail(String email);
    Boolean authenticate(String username, String password);

    Funcionario get(Veterinario veterinario);

    Optional<Veterinario> create(Veterinario veterinario);
    Optional<Veterinario> create(VeterinarioDto veterinarioDto);

    Veterinario save(Veterinario veterinario);

    void deleteById(Long id);


    Veterinario createResponsavelTecnico(CadastroClinicaDto cadastro, Clinica clinica) throws RuntimeException;
}
