package br.vet.sidekick.poc.repository;

import br.vet.sidekick.poc.controller.model.TipoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoDocumentoRepository extends JpaRepository<TipoDocumento, Long> {
}
