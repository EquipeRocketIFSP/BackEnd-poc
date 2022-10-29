package br.vet.sidekick.poc.controller;

import br.vet.sidekick.poc.controller.model.Animal;
import br.vet.sidekick.poc.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cadastro-animal")
public class AnimalController {
    @Autowired
    private AnimalRepository animalRepository;


    //tratar com List como
    public ResponseEntity<Animal> registerAnimal(Animal animal){
        Animal addedAnimal = animalRepository.save(animal);
        return ResponseEntity.ok(animal);
    }
}
