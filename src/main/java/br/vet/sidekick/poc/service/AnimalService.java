package br.vet.sidekick.poc.service;

import br.vet.sidekick.poc.model.Animal;
import org.springframework.stereotype.Service;

@Service
public interface AnimalService {
    Animal save(Animal animal);

    void deleteById(Long id);
}
