package br.vet.sidekick.poc.service;

import br.vet.sidekick.poc.model.Prontuario;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public interface ProntuarioService {
    Prontuario save(Prontuario prontuario) throws Exception;

    boolean exists(LocalDateTime dataAtendimento);

    Optional<List<Prontuario>> getByCodigo(String codigo);

}
