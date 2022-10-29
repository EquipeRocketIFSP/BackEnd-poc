package br.vet.sidekick.poc.repository;

import br.vet.sidekick.poc.controller.model.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
}
