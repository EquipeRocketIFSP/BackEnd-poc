package br.vet.sidekick.poc.repository;

import br.vet.sidekick.poc.model.Clinica;
import br.vet.sidekick.poc.model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Long> {

    boolean existsByCpfAndClinica(String cpf, Long clinica);

    List<Tutor> findAllByClinica(Clinica clinica);

    List<Tutor> findAllByClinica(Long clinicaId);

    Optional<Tutor> findByIdAndClinica(Long id, Long clinicaFromFuncionarioAuthenticated);

    Optional<Tutor> findByCpf(String cpf);
}
