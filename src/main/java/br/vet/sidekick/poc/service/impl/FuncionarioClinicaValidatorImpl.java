package br.vet.sidekick.poc.service.impl;

import br.vet.sidekick.poc.model.Clinica;
import br.vet.sidekick.poc.model.Prontuario;
import br.vet.sidekick.poc.repository.ProntuarioRepository;
import br.vet.sidekick.poc.service.FuncionarioClinicaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioClinicaValidatorImpl implements FuncionarioClinicaValidator {

    @Autowired
    private ProntuarioRepository prontuarioRepository;

    @Override
    public Boolean isRequestedDocumento_CodigoFromClinica(
            final Prontuario prontuario,
            final Clinica clinica
    ) {
        return prontuarioRepository.existsByCodigo(prontuario.getCodigo());
    }

}
