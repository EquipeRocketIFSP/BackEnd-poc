package br.vet.sidekick.poc.repository;

import br.vet.sidekick.poc.model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Long> {
    boolean findByCpf(String cpf);
}
