package br.vet.sidekick.poc.repository;

import br.vet.sidekick.poc.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    Boolean existsByUserNameAndPassword(String username, String password);

    Funcionario getByUserName(String username);
}
