package br.vet.sidekick.poc.service;

import br.vet.sidekick.poc.model.Animal;

public interface AnimalService {
    Animal save(Animal animal);

    void deleteById(Long id);
}
