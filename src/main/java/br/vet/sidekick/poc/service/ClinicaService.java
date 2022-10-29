package br.vet.sidekick.poc.service;

import br.vet.sidekick.poc.controller.dto.CadastroClinicaDto;
import org.springframework.stereotype.Service;

@Service
public interface ClinicaService {
    void cadastrar(CadastroClinicaDto cadastroClinicaDto);
}
