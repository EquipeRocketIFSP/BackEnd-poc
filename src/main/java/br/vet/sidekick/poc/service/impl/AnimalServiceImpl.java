package br.vet.sidekick.poc.service.impl;

import br.vet.sidekick.poc.model.Animal;
import br.vet.sidekick.poc.repository.AnimalRepository;
import br.vet.sidekick.poc.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    @Override
    public Animal save(Animal animal){
        //TODO: validar se o usuario existe
        return animalRepository.save(animal);
    }

    @Override
    public void deleteById(Long id){
        animalRepository.deleteById(id);
    }
}
