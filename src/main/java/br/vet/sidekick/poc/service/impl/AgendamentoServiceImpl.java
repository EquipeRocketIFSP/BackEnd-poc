package br.vet.sidekick.poc.service.impl;

import br.vet.sidekick.poc.controller.dto.CadastroAgendamentoDto;
import br.vet.sidekick.poc.exceptionResolver.exception.AgendamentoCreateException;
import br.vet.sidekick.poc.exceptionResolver.exception.AgendamentoNotFound;
import br.vet.sidekick.poc.model.Agendamento;
import br.vet.sidekick.poc.model.Animal;
import br.vet.sidekick.poc.model.Clinica;
import br.vet.sidekick.poc.repository.AgendamentoRepository;
import br.vet.sidekick.poc.repository.AnimalRepository;
import br.vet.sidekick.poc.repository.ClinicaRepository;
import br.vet.sidekick.poc.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AgendamentoServiceImpl implements AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private ClinicaRepository clinicaRepository;

    @Override
    public Agendamento create(CadastroAgendamentoDto agendamentoDto) throws AgendamentoCreateException {
        Optional<Clinica> optClinica = this.clinicaRepository.findById(agendamentoDto.getClinica());

        if (optClinica.isEmpty())
            throw new AgendamentoCreateException("Clinica não encontrada.");

        Optional<Animal> optAnimal = this.animalRepository.findById(agendamentoDto.getAnimal());

        if (optAnimal.isEmpty())
            throw new AgendamentoCreateException("Animal não encontrado.");

        LocalDateTime data = LocalDateTime.parse(agendamentoDto.getDataConsulta().replace("Z", ""));
        Optional<Agendamento> optAgendamento = this.agendamentoRepository.findByAnimalAndDataConsulta(optAnimal.get(), data);

        if (optAgendamento.isPresent())
            throw new AgendamentoCreateException("Esse animal já possui um agendamento para esse horário.");

        Agendamento agendamento = new Agendamento();

        agendamento.setTipoConsulta(agendamentoDto.getTipoConsulta());
        agendamento.setDataConsulta(data);
        agendamento.setClinica(optClinica.get());
        agendamento.setAnimal(optAnimal.get());

        return agendamentoRepository.save(agendamento);
    }

    @Override
    public Agendamento getOne(Long id) throws AgendamentoNotFound {
        Optional<Agendamento> optAgendamento = this.agendamentoRepository.findById(id);

        if (optAgendamento.isEmpty())
            throw new AgendamentoNotFound();

        return optAgendamento.get();
    }

    @Override
    public List<LocalDateTime> getScheduleConsultsDates() {
        List<Agendamento> agendamentos = this.agendamentoRepository.findByDataConsultaGreaterThan(LocalDateTime.now());

        return agendamentos.stream()
                .map(Agendamento::getDataConsulta)
                .collect(Collectors.toList());
    }
}
