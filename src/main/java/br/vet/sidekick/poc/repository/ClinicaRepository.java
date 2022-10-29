package br.vet.sidekick.poc.repository;

import br.vet.sidekick.poc.controller.model.Clinica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicaRepository extends JpaRepository<Clinica, Long> {
}
