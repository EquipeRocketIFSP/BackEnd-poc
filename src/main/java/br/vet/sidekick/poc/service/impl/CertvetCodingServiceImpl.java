package br.vet.sidekick.poc.service.impl;

import br.vet.sidekick.poc.model.Prontuario;
import br.vet.sidekick.poc.service.CertvetCodingService;
import br.vet.sidekick.poc.service.ClinicaService;
import br.vet.sidekick.poc.service.ProntuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CertvetCodingServiceImpl implements CertvetCodingService {

    @Autowired
    private ProntuarioService prontuarioService;


    @Override
    public Boolean checkExistingClinica(String certvetClinica) {
        return null;
    }

    @Override
    public Optional<String> checkExistingProntuario(String certvetCode) {
        return prontuarioService.findByCertvetProntuario(certvetCode);
    }
}
