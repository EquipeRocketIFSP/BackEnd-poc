package br.vet.sidekick.poc.service;

import br.vet.sidekick.poc.model.Veterinario;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface VeterinarioService {
    Optional<Veterinario> findByEmail(String email);
}
