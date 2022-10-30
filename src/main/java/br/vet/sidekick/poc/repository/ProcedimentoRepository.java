package br.vet.sidekick.poc.repository;

import br.vet.sidekick.poc.model.Procedimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcedimentoRepository extends JpaRepository<Procedimento, Long> {
}
