package br.vet.sidekick.poc.service;

import br.vet.sidekick.poc.model.Animal;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface AnimalService {
    Optional<Animal> create(Animal animal);
}
