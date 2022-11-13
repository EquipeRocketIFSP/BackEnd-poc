package br.vet.sidekick.poc.service.impl;

import br.vet.sidekick.poc.model.Tutor;
import br.vet.sidekick.poc.repository.TutorRepository;
import br.vet.sidekick.poc.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class TutorServiceImpl implements TutorService {

    @Autowired
    private TutorRepository tutorRepository;

    @Override
    public Optional<Tutor> create(Tutor tutor) {
        //TODO: precisa checar se existe, antes de registrar um tutor. incluir id da clinica em findByCpf()
//        if (tutorRepository.findByCpf(tutor.getCpf()))
//            return Optional.empty();
//        return Optional.of(tutorRepository.save(tutor));
        return Optional.of(tutorRepository.save(tutor));
    }
}
