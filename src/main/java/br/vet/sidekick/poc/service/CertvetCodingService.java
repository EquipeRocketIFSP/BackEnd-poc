package br.vet.sidekick.poc.service;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface CertvetCodingService {
    Boolean checkExistingClinica(String certvetClinica);

    Optional<String> checkExistingProntuario(String certvetProntuario);
}
