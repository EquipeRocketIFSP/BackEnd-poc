package br.vet.sidekick.poc.service.impl;

import br.vet.sidekick.poc.model.Animal;
import br.vet.sidekick.poc.repository.AnimalRepository;
import br.vet.sidekick.poc.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    @Override
    public Optional<Animal> create(Animal animal) {
        if (animalRepository.existsById(animal.getId()))
            return Optional.empty();
        return Optional.of(animalRepository.save(animal));
    }

}
