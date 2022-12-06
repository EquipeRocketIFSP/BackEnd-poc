package br.vet.sidekick.poc.repository;

import br.vet.sidekick.poc.model.Agendamento;

import java.time.LocalDateTime;

import br.vet.sidekick.poc.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    List<Agendamento> findByDataConsultaGreaterThan(LocalDateTime data);

    Optional<Agendamento> findByAnimalAndDataConsulta(Animal animal, LocalDateTime data);
}
