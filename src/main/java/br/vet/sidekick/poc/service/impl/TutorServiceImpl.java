package br.vet.sidekick.poc.service.impl;

import br.vet.sidekick.poc.controller.dto.CadastroTutorDto;
import br.vet.sidekick.poc.model.Clinica;
import br.vet.sidekick.poc.model.Tutor;
import br.vet.sidekick.poc.repository.ClinicaRepository;
import br.vet.sidekick.poc.repository.TutorRepository;
import br.vet.sidekick.poc.service.TutorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@Slf4j
public class TutorServiceImpl implements TutorService {

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private ClinicaRepository clinicaRepository;

    @Override
    public Optional<Tutor> create(CadastroTutorDto cadastroTutorDto) {
        Tutor tutor = convert(cadastroTutorDto);
        //TODO: precisa checar se existe, antes de registrar um tutor. incluir id da clinica em findByCpf()
        if (tutorRepository.existsByCpfAndClinica(tutor.getCpf(), tutor.getClinica()))
            return Optional.empty();
        Clinica clinica = clinicaRepository.getReferenceById(tutor.getClinica());
        tutor.setClinica(clinica.getId());
        log.info("Tutor cadastrado: " + tutor);
        return Optional.of(tutorRepository.save(tutor));
    }

    private Tutor convert(CadastroTutorDto cadastroTutorDto){
        return Tutor.builder()
                .nome(cadastroTutorDto.getNome())
                .cpf(cadastroTutorDto.getCpf())
                .rg(cadastroTutorDto.getRg())
                .cep(cadastroTutorDto.getCep())
                .logradouro(cadastroTutorDto.getLogradouro())
                .numero(cadastroTutorDto.getNumero())
                .bairro(cadastroTutorDto.getBairro())
                .cidade(cadastroTutorDto.getCidade())
                .estado(cadastroTutorDto.getEstado())
                .celular(cadastroTutorDto.getCelular())
                .email(cadastroTutorDto.getEmail())
                .telefone(cadastroTutorDto.getTelefone())
                .clinica(
//                        clinicaRepository.getReferenceById(cadastroTutorDto.getClinica())
                        cadastroTutorDto.getClinica()
                )
                .build();
    }
}
