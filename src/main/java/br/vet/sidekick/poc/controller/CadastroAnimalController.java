package br.vet.sidekick.poc.controller;

import br.vet.sidekick.poc.controller.dto.CadastroAnimalDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@CrossOrigin
@RestController("/cadastro-animal")
public class CadastroAnimalController {


    private CadastroAnimalDto cadastroAnimalDto = CadastroAnimalDto.getMock();

    private List<CadastroAnimalDto> list = Arrays.asList(cadastroAnimalDto);

    @PostMapping
    public ResponseEntity<CadastroAnimalDto> registerAnimal(
            @RequestBody CadastroAnimalDto animal
    ){
        list.add(animal);
        return ResponseEntity.created(
                URI.create("/animal/" + String.valueOf(list.indexOf(animal))
                )).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<CadastroAnimalDto> getOne(
            @RequestParam Integer id
    ){
        CadastroAnimalDto animal = list.get(id);
        return animal != null
                ? ResponseEntity.ok(animal)
                : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<CadastroAnimalDto>> getAll(){
        return list.size() == 0
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(list);
    }

}
