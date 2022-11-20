package br.vet.sidekick.poc.service;

import br.vet.sidekick.poc.model.Funcionario;
import br.vet.sidekick.poc.model.Veterinario;

import java.util.Optional;

public interface VeterinarioService {
    Boolean authenticate(String username, String password);

    Funcionario get(Veterinario veterinario);

    Optional<Veterinario> create(Veterinario veterinario);

    Veterinario save(Veterinario veterinario);

    void deleteById(Long id);
}
