package br.vet.sidekick.poc.repository;

import br.vet.sidekick.poc.controller.model.Documento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentoRepository extends JpaRepository<Documento, Long> {
}
