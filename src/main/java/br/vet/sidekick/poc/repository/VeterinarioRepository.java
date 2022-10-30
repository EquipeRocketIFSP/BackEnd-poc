package br.vet.sidekick.poc.repository;

import br.vet.sidekick.poc.model.Veterinario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeterinarioRepository extends JpaRepository<Veterinario, Long> {
}
