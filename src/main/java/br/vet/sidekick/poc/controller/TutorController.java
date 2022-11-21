package br.vet.sidekick.poc.controller;

import br.vet.sidekick.poc.model.Animal;
import br.vet.sidekick.poc.model.Tutor;
import br.vet.sidekick.poc.repository.TutorRepository;
import br.vet.sidekick.poc.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/tutor")
public class TutorController {

    @Autowired
    private TutorService tutorService;

    @Autowired
    private TutorRepository tutorRepository;

    @PostMapping
    public ResponseEntity<Tutor> registerTutor(@RequestBody Tutor tutor) {
        Optional<Tutor> referenceTutor = tutorService.create(tutor);
        if (referenceTutor.isEmpty())
            return ResponseEntity.badRequest().build();
        return ResponseEntity.created(
                URI.create("/tutor/" + tutor.getId().toString())
        ).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tutor> getOne(@PathVariable Long id) {
        Optional<Tutor> referenceTutor = tutorRepository.findById(id);

        if (referenceTutor.isEmpty())
            return ResponseEntity.notFound().build();

        Tutor tutor = referenceTutor.get();
        List<Animal> animais = referenceTutor.get().getAnimais();

        animais.forEach(animal -> animal.setTutores(null));
        tutor.setAnimais(animais);

        return ResponseEntity.ok(tutor);
    }

    @GetMapping
    public ResponseEntity<List<Tutor>> getAll() {
        List<Tutor> tutores = tutorRepository.findAll();

        tutores.forEach((tutor) -> tutor.setAnimais(null));

        return ResponseEntity.ok(tutores);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Tutor> updateTutor(
            @PathVariable Long id,
            @RequestBody Tutor updateTutor
    ){
        if (tutorRepository.existsById(id)){
            Tutor tutor = tutorService.updateTutor(updateTutor);
            return ResponseEntity.ok(tutor);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Tutor> deleteTutor(
            @PathVariable Long id
    ) {
        if (tutorRepository.existsById(id)) {
            tutorRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
