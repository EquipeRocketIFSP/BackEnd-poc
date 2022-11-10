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

@CrossOrigin
@RestController
@RequestMapping("/animal")
public class AnimalController {

    @Autowired
    private AnimalRepository animalRepository;
    private static CadastroAnimalDto cadastroAnimalDto = CadastroAnimalDto.getMock();

    private static List<CadastroAnimalDto> list = new ArrayList<>();

    static {list.add(cadastroAnimalDto);}

    private AnimalService animalService;

    @PostMapping
    public ResponseEntity<Animal> registerAnimal(
            @RequestBody Animal animal
    ){
        try {
            animal = animalService.save(animal);
            return ResponseEntity.created(
                    URI.create("/animal/" + String.valueOf(animal.getId())
                    )).build();
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Animal> delete(
            @PathVariable Long id
    ) {
        try {
            animalService.deleteById(id);
            return ResponseEntity.noContent().build();

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

//    @PostMapping
//    public ResponseEntity<CadastroAnimalDto> registerAnimal(
//            @RequestBody CadastroAnimalDto animal
//    ){
//        list.add(animal);
//        return ResponseEntity.created(
//                URI.create("/animal/" + String.valueOf(list.indexOf(animal))
//                )).build();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<CadastroAnimalDto> getOne(
//            @PathVariable Integer id
//    ){
//        CadastroAnimalDto animal = list.get(id);
//        return animal != null
//                ? ResponseEntity.ok(animal)
//                : ResponseEntity.notFound().build();
//    }
//
//    @GetMapping
//    public ResponseEntity<List<CadastroAnimalDto>> getAll(){
//        return list.size() == 0
//                ? ResponseEntity.notFound().build()
//                : ResponseEntity.ok(list);
//    }

}
