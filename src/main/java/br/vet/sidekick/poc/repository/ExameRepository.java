package br.vet.sidekick.poc.repository;

import br.vet.sidekick.poc.model.Exame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExameRepository extends JpaRepository<Exame, Long> {
}
