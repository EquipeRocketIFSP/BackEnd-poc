package br.vet.sidekick.poc.service;

import br.vet.sidekick.poc.model.Tutor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public interface TutorService {

    Optional<Tutor> create(Tutor tutor);

    Tutor updateTutor(Tutor updateTutor);
}
