package br.vet.sidekick.poc.service.impl;

import br.vet.sidekick.poc.controller.dto.CadastroAgendamentoDto;
import br.vet.sidekick.poc.model.Agendamento;
import br.vet.sidekick.poc.repository.AgendamentoRepository;
import br.vet.sidekick.poc.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AgendamentoServiceImpl implements AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Override
    public Optional<Agendamento> create(Agendamento agendamento) {

        //classe animal criada, quando fazer agendamento puxar o animal do bd, setar ele no agendamento e salvar o agendamento no bd

        if (agendamentoRepository.existsById(agendamento.getId()))
            return Optional.empty();

        return Optional.of(agendamentoRepository.save(agendamento));
    }

    @Override
    public Optional<Agendamento> get(Long id) {
        if (agendamentoRepository.existsById(id)){
            return agendamentoRepository.findById(id);
        }
        return Optional.empty();
    }


}
