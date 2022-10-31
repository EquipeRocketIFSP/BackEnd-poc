package br.vet.sidekick.poc.controller;

import br.vet.sidekick.poc.controller.dto.CadastroAnimalDto;
import br.vet.sidekick.poc.controller.dto.CadastroClinicaDto;
import br.vet.sidekick.poc.repository.AnimalRepository;
import br.vet.sidekick.poc.service.AnimalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cadastro-animal")
@CrossOrigin
@Slf4j
public class CadastroAnimalController {

    @Autowired
    private AnimalService animalService;

    @PostMapping
    public ResponseEntity<CadastroAnimalDto> registerAnimal(
            @RequestBody CadastroAnimalDto cadastro){
        animalService.create(cadastro);
        return ResponseEntity.ok(cadastro);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<CadastroAnimalDto> getOne(@PathVariable Long id){
//        return id != 1L
//                ? ResponseEntity.notFound().build()
//                : ResponseEntity.ok(AnimalRepository);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<CadastroClinicaDto>> getAll(){
//        return ResponseEntity.ok(AnimalRepository);
//    }

}
