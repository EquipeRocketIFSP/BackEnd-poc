package br.vet.sidekick.poc.repository;

import br.vet.sidekick.poc.model.Prontuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProntuarioRepository extends JpaRepository<Prontuario, Long> {
}
