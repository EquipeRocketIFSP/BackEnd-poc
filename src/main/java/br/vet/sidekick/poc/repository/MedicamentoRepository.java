package br.vet.sidekick.poc.repository;

import br.vet.sidekick.poc.controller.model.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
}
