package br.vet.sidekick.poc.repository;

import br.vet.sidekick.poc.model.Veterinario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface VeterinarioRepository extends JpaRepository<Veterinario, Long> {

    Boolean existsByUsernameAndPassword(String username, String password);

    Optional<Veterinario> findByEmail(String email);
    Veterinario findByUsername(String username);
    Veterinario getByUsername(String username);

    Boolean existsByUsername(String username);

    boolean existsByRegistroCRMVAndClinica_id(String crmv, Long id);
}
