package br.vet.sidekick.poc.repository;

import br.vet.sidekick.poc.controller.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
