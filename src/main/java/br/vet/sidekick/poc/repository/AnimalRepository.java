package br.vet.sidekick.poc.repository;

import br.vet.sidekick.poc.model.Animal;
import br.vet.sidekick.poc.model.Clinica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    List<Animal> findAllByClinicaId(Long id);
    List<Animal> findAllByClinicaId(Clinica clinica);

    Optional<Animal> findByTutores_idAndNome(Long id, String nome);

    List<Animal> findAllByTutores_idAndNome(Long id, String nome);
}
