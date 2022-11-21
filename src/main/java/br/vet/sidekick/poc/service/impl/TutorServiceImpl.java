package br.vet.sidekick.poc.service.impl;

import br.vet.sidekick.poc.model.Clinica;
import br.vet.sidekick.poc.model.Tutor;
import br.vet.sidekick.poc.repository.ClinicaRepository;
import br.vet.sidekick.poc.repository.TutorRepository;
import br.vet.sidekick.poc.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class TutorServiceImpl implements TutorService {

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private ClinicaRepository clinicaRepository;

    @Override
    public Optional<Tutor> create(Tutor tutor) {
        if (tutorRepository.existsByCpfAndClinica(tutor.getCpf(), tutor.getClinica()))
            return Optional.empty();
        Clinica clinica = clinicaRepository.getReferenceById(tutor.getClinica());
        tutor.setClinica(clinica.getId());

        return Optional.of(tutorRepository.save(tutor));
    }

    @Override
    public Tutor updateTutor(Tutor updateTutor) {
        Tutor tutor = tutorRepository.getReferenceById(updateTutor.getId());

        tutor.setBairro(updateTutor.getBairro());
        tutor.setCelular(updateTutor.getCelular());
        tutor.setCep(updateTutor.getCep());
        tutor.setCidade(updateTutor.getCidade());
        tutor.setClinica(updateTutor.getClinica());
        tutor.setCpf(updateTutor.getCpf());
        tutor.setEstado(updateTutor.getEstado());
        tutor.setEmail(updateTutor.getEmail());
        tutor.setLogradouro(updateTutor.getLogradouro());
        tutor.setNome(updateTutor.getNome());
        tutor.setNumero(updateTutor.getNumero());
        tutor.setRg(updateTutor.getRg());
        tutor.setTelefone(updateTutor.getTelefone());

        tutor = tutorRepository.save(tutor);
        return tutor;

    }
}
