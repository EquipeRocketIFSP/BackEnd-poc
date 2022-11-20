package br.vet.sidekick.poc.service;

import br.vet.sidekick.poc.model.Clinica;
import br.vet.sidekick.poc.model.Prontuario;
import org.springframework.stereotype.Service;

@Service
public interface FuncionarioClinicaValidator {
    Boolean isRequestedDocumento_CodigoFromClinica(Prontuario prontuario, Clinica clinica);
}
