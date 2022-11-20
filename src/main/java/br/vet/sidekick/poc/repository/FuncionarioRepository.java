package br.vet.sidekick.poc.repository;

import br.vet.sidekick.poc.model.Clinica;
import br.vet.sidekick.poc.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    Boolean existsByUsernameAndPassword(String username, String password);

    Optional<Funcionario> findByEmail(String email);
    Funcionario findByUsername(String username);
    Funcionario getByUsername(String username);

    Boolean existsByUsername(String username);

}
