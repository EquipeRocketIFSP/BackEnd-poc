package br.vet.sidekick.poc.repository;

import br.vet.sidekick.poc.model.TipoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoDocumentoRepository extends JpaRepository<TipoDocumento, Long> {
    TipoDocumento findByDescricao(String prontuario);
}
