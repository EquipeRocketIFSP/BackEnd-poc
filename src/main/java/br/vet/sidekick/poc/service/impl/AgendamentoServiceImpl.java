package br.vet.sidekick.poc.service.impl;

import br.vet.sidekick.poc.controller.dto.AgendamentoDto;
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
    public Optional<Agendamento> create(AgendamentoDto agendamento) {

//        if (agendamentoRepository.existsById(agendamento.getId()))
//            return Optional.empty();
        return Optional.of(
                agendamentoRepository.save(
                        Agendamento.builder()
                                .criadoEm(agendamento.getCriadoEm())
                                .dataConsulta(agendamento.getDataConsulta())
                                .clinica(clinicaRepository.getReferenceById(agendamento.getClinica()))
                                .animal(animalRepository.getReferenceById(agendamento.getAnimal()))
                                .tipoConsulta(agendamento.getTipoConsulta())
                                .build()
                )
        );
    }

}
