package br.vet.sidekick.poc.service;

import br.vet.sidekick.poc.controller.dto.CadastroClinicaDto;
import br.vet.sidekick.poc.model.Clinica;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public interface ClinicaService {
    Optional<Clinica> create(CadastroClinicaDto cadastro);
}
