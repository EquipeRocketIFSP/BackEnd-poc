package br.vet.sidekick.poc.repository;

import br.vet.sidekick.poc.model.Documento;
import br.vet.sidekick.poc.model.Prontuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long> {
    Optional<List<Prontuario>> findAllByVeterinario_IdAndDateCreationBetween(Long veterinarioId, LocalDateTime minusSeconds, LocalDateTime now);
}
