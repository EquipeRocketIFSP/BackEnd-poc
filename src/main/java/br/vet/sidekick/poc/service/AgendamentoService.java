package br.vet.sidekick.poc.service;

import br.vet.sidekick.poc.controller.dto.CadastroAgendamentoDto;
import br.vet.sidekick.poc.model.Agendamento;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface AgendamentoService {
    Optional<Agendamento> create(CadastroAgendamentoDto agendamento);

    Optional<Agendamento> get(Long id);
}
