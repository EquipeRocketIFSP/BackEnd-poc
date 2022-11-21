package br.vet.sidekick.poc.service;

import br.vet.sidekick.poc.controller.dto.CadastroClinicaDto;
import br.vet.sidekick.poc.model.Clinica;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ClinicaService {
    Optional<Clinica> create(CadastroClinicaDto cadastro);
    Optional<Clinica> getById(Long id);
    List<Clinica> getAll();
}
