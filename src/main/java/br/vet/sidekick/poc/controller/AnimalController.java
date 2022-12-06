package br.vet.sidekick.poc.controller;

import br.vet.sidekick.poc.conf.security.service.TokenService;
import br.vet.sidekick.poc.controller.dto.CadastroAnimalDto;
import br.vet.sidekick.poc.controller.dto.ListagemTutoresAnimalDto;
import br.vet.sidekick.poc.model.Animal;
import br.vet.sidekick.poc.model.Funcionario;
import br.vet.sidekick.poc.model.Tutor;
import br.vet.sidekick.poc.repository.AnimalRepository;
import br.vet.sidekick.poc.service.AnimalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@CrossOrigin
@RestController
@RequestMapping("/animal")
@Slf4j
public class AnimalController extends BaseController {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private AnimalService animalService;

    @PostMapping
    public ResponseEntity<Animal> registerAnimal(
            @RequestBody CadastroAnimalDto animalDto,
            @RequestHeader(AUTHORIZATION) String auth
    ) {
        Long clinicaId = getClinicaFromRequester(auth);
        Optional<Animal> referenceAnimal = animalService.create(animalDto, clinicaId);
        return referenceAnimal.isEmpty()
                ? ResponseEntity.badRequest().build()
                : ResponseEntity.created(URI.create("/animal/" + referenceAnimal.get().getId().toString())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> getOne(@PathVariable Long id) {
        Optional<Animal> referenceAnimal = animalRepository.findById(id);
        return referenceAnimal.isPresent()
                ? ResponseEntity.ok().body(referenceAnimal.get())
                : ResponseEntity.notFound().build();

    }

    @GetMapping("/{id}/tutores")
    public ResponseEntity<List<ListagemTutoresAnimalDto>> getTutoresFromAnimal(@PathVariable Long id) {
        List<Tutor> tutores = this.animalService.getAllTutoresByAnimal(id);
        List<ListagemTutoresAnimalDto> tutoresAnimalDtos = tutores.stream()
                .map((tutor) -> new ListagemTutoresAnimalDto(tutor))
                .collect(Collectors.toList());

        return ResponseEntity.ok(tutoresAnimalDtos);
    }

    @GetMapping
    public ResponseEntity<List<Animal>> getAll(
            @RequestHeader(AUTHORIZATION) String auth
    ) {
        return ResponseEntity.ok()
                .body(animalRepository.findAllByClinica(getClinicaFromRequester(auth)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Animal> deleteAnimal(
            @PathVariable Long id
    ) {
        if (animalRepository.existsById(id)) {
            animalRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    //TODO
    @PutMapping
    public ResponseEntity<Animal> updateAnimal() {
        return null;
    }

}
