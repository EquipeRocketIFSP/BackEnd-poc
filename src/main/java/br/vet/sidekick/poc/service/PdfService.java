package br.vet.sidekick.poc.service;

import br.vet.sidekick.poc.model.Animal;
import br.vet.sidekick.poc.model.Prontuario;
import br.vet.sidekick.poc.model.Tutor;
import br.vet.sidekick.poc.model.Veterinario;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public interface PdfService {
    byte[] termoAutorizacaoProcedimentoCirurgico(
            String estabelecimento,
            String procedimento,
            String cidade,
            Animal animal,
            Veterinario veterinario,
            Tutor tutor
    ) throws Exception;

    byte[] writeProntuario(Prontuario prontuario) throws Exception;

    byte[] retrieveFromRepository(Prontuario prontuario) throws IOException;

}
