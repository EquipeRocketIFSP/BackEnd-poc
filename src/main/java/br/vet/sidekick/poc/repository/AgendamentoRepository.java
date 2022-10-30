package br.vet.sidekick.poc.repository;

import br.vet.sidekick.poc.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
}
