package br.vet.sidekick.poc.service.impl;

import br.vet.sidekick.poc.model.Agendamento;
import br.vet.sidekick.poc.model.Animal;
import br.vet.sidekick.poc.model.Clinica;
import br.vet.sidekick.poc.repository.AgendamentoRepository;
import br.vet.sidekick.poc.repository.AnimalRepository;
import br.vet.sidekick.poc.repository.ClinicaRepository;
import br.vet.sidekick.poc.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AgendamentoServiceImpl implements AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private ClinicaRepository clinicaRepository;

    @Override
    public Optional<Agendamento> create(Agendamento agendamento) {

//        if (agendamentoRepository.existsById(agendamento.getId()))
//            return Optional.empty();

        Animal animalId = animalRepository.getReferenceById(agendamento.getAnimal());
        agendamento.setAnimal(animalId.getId());

        Clinica clinicaId = clinicaRepository.getReferenceById(agendamento.getClinica());
        agendamento.setClinica(clinicaId.getId());

        return Optional.of(agendamentoRepository.save(agendamento));
    }

    @Override
    public Agendamento updateAgendamento(Agendamento updateAgendamento) {
        Agendamento agendamento = agendamentoRepository.getReferenceById(updateAgendamento.getId());

        agendamento.setAnimal(updateAgendamento.getAnimal());
        agendamento.setClinica(updateAgendamento.getClinica());
        agendamento.setDataConsulta(updateAgendamento.getDataConsulta());
        agendamento.setTipoConsulta(updateAgendamento.getTipoConsulta());

        agendamento = agendamentoRepository.save(agendamento);

        return agendamento;

    }

}
