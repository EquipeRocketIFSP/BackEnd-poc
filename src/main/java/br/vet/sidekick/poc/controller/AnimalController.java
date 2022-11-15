package br.vet.sidekick.poc.controller;

import br.vet.sidekick.poc.controller.dto.CadastroAnimalDto;
import br.vet.sidekick.poc.model.Animal;
import br.vet.sidekick.poc.repository.AnimalRepository;
import br.vet.sidekick.poc.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/animal")
public class AnimalController {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private AnimalService animalService;
    private static CadastroAnimalDto cadastroAnimalDto = CadastroAnimalDto.getMock();

    //TODO: remover depois
    private static List<CadastroAnimalDto> list = new ArrayList<>();
    static {list.add(cadastroAnimalDto);}

    @PostMapping
    public ResponseEntity<Animal> registerAnimal(
            @RequestBody Animal animal
    ){
        Optional<Animal> referenceAnimal = animalService.create(animal);
        if (referenceAnimal.isEmpty())
            return ResponseEntity.badRequest().build();
        return ResponseEntity.created(
                URI.create("/animal/" + animal.getId().toString())
        ).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> getOne(
            @PathVariable Long id
    ){
        Optional<Animal> referenceAnimal = animalRepository.findById(id);
        if (referenceAnimal.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(animalRepository.getReferenceById(id));
    }

    @GetMapping
    public ResponseEntity<List<Animal>> getAll(){
        List<Animal> animais = animalRepository.findAll();
        if (animais.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(animais);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Animal> deleteAnimal(
            @PathVariable Long id
    ) {
        if (animalRepository.existsById(id)){
            animalRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    //TODO
    @PutMapping
    public ResponseEntity<Animal> updateAnimal(){ return null;}

}
