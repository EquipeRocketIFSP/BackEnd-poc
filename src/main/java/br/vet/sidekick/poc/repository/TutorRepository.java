package br.vet.sidekick.poc.repository;

import br.vet.sidekick.poc.model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorRepository extends JpaRepository<Tutor, Long> {
}
