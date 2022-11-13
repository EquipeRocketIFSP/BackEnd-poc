package br.vet.sidekick.poc.service.impl;

import br.vet.sidekick.poc.model.Tutor;
import br.vet.sidekick.poc.repository.TutorRepository;
import br.vet.sidekick.poc.service.TutorService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TutorServiceImpl implements TutorService {

    private TutorRepository tutorRepository;

    @Override
    public Optional<Tutor> create(Tutor tutor) {
//        if (tutorRepository.existsById(tutor.getId()))
//            return Optional.empty();
        return Optional.of(tutorRepository.save(tutor));
    }
}
