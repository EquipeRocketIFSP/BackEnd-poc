package br.vet.sidekick.poc.repository;

import br.vet.sidekick.poc.model.Prontuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProntuarioRepository extends JpaRepository<Prontuario, Long> {
    boolean existsByDataAtendimento(LocalDateTime dataAtendimento);

    Optional<List<Prontuario>> findAllByCodigo(String codigo);
}
