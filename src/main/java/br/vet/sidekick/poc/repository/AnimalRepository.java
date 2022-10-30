package br.vet.sidekick.poc.repository;

import br.vet.sidekick.poc.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
