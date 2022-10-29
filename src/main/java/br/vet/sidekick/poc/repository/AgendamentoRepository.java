package br.vet.sidekick.poc.repository;

import br.vet.sidekick.poc.controller.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
}
