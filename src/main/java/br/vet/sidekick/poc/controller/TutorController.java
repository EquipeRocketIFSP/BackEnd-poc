package br.vet.sidekick.poc.controller;

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
    public ResponseEntity<Tutor> getOne(
            @PathVariable Long id
    ){
        Optional<Tutor> referenceTutor = tutorRepository.findById(id);
        if (referenceTutor.isEmpty())
            return ResponseEntity.notFound().build();
        return  ResponseEntity.ok(tutorRepository.getOne(id));
    }

    @GetMapping
    public ResponseEntity<List<Tutor>> getAll(){
        if (tutorRepository.findAll().isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(tutorRepository.findAll());
    }

    //TODO: arrumar lógica do put method
//    @PutMapping("/editar/{id}")
//    public ResponseEntity<Tutor> updateTutor(
//            @PathVariable Long id,
//            @RequestBody Tutor tutor
//    ){
//        if (tutorRepository.existsById(id)){
//            tutorRepository.findById(id)
//                    .map(updatedTutor -> {
//                        tutor.setNome(updatedTutor.getNome());
//                        tutor.setCpf(updatedTutor.getCpf());
//                        tutor.setRg(updatedTutor.getRg());
//                        tutor.setEmail(updatedTutor.getEmail());
//                        tutor.setTelefone(updatedTutor.getTelefone());
//                        tutor.setCelular(updatedTutor.getCelular());
//                        tutor.setAnimais(updatedTutor.getAnimais());
//                        tutor.setCep(updatedTutor.getCep());
//                        tutor.setLogradouro(updatedTutor.getLogradouro());
//                        tutor.setNumero(updatedTutor.getNumero());
//                        tutor.setBairro(updatedTutor.getBairro());
//                        tutor.setCidade(updatedTutor.getCidade());
//                        tutor.setEstado(updatedTutor.getEstado());
//                        tutorRepository.save(tutor);
//
//                        return ResponseEntity.ok(tutor);
//                    });
//        }
//
//        return ResponseEntity.notFound().build();
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Tutor> deleteTutor(
            @PathVariable Long id
    ){
        if (tutorRepository.existsById(id)){
            tutorRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
