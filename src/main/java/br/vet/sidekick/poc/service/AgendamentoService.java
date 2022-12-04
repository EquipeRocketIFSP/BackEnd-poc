package br.vet.sidekick.poc.service;

import br.vet.sidekick.poc.controller.dto.CadastroAgendamentoDto;
import br.vet.sidekick.poc.model.Agendamento;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public interface AgendamentoService {
    Agendamento create(CadastroAgendamentoDto agendamentoDto);

    Agendamento getOne(Long id);

    List<LocalDateTime> getScheduleConsultsDates();
}
