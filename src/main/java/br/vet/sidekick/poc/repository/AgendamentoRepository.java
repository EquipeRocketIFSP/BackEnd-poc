package br.vet.sidekick.poc.repository;

import br.vet.sidekick.poc.model.Agendamento;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    List<Agendamento> findByDataConsultaGreaterThan(LocalDateTime data);
}
