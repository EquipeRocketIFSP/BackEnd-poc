package br.vet.sidekick.poc.repository;

import br.vet.sidekick.poc.model.Cirurgia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CirurgiaRepository extends JpaRepository<Cirurgia, Long> {
}
