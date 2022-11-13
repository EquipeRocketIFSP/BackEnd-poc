package br.vet.sidekick.poc.controller;

import br.vet.sidekick.poc.model.Tutor;
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

    @PostMapping
    public ResponseEntity<Tutor> registerTutor(
            @RequestBody Tutor tutor
    ){
        Optional<Tutor> referenceTutor = tutorService.create(tutor);
        if (referenceTutor.isEmpty())
            return ResponseEntity.badRequest().build();
        return ResponseEntity.created(
                URI.create("/tutor/" + tutor.getId().toString())
        ).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tutor> getOne(){
        return  null;
    }

    @GetMapping
    public ResponseEntity<List<Tutor>> getAll(){
        return null;
    }

    public void updateTutor(){}

    public void deleteTutor(){}
}
