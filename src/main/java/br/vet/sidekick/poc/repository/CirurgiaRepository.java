package br.vet.sidekick.poc.repository;

import br.vet.sidekick.poc.controller.model.Cirurgia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CirurgiaRepository extends JpaRepository<Cirurgia, Long> {
}
