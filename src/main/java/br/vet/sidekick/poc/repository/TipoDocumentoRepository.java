package br.vet.sidekick.poc.repository;

import br.vet.sidekick.poc.model.TipoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoDocumentoRepository extends JpaRepository<TipoDocumento, Long> {
}
