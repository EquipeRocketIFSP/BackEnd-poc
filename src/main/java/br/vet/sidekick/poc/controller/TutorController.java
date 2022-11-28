package br.vet.sidekick.poc.controller;

import br.vet.sidekick.poc.conf.security.service.TokenService;
import br.vet.sidekick.poc.controller.dto.CadastroTutorDto;
import br.vet.sidekick.poc.model.Animal;
import br.vet.sidekick.poc.model.Tutor;
import br.vet.sidekick.poc.repository.TutorRepository;
import br.vet.sidekick.poc.service.TutorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@CrossOrigin
@RestController
@RequestMapping("/tutor")
@Slf4j
public class TutorController extends BaseController {

    @Autowired
    private TutorService tutorService;

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<Tutor> registerTutor(@RequestBody CadastroTutorDto tutorDto) {
        log.info("Tutor a ser cadastrado: " + tutorDto);
        Optional<Tutor> referenceTutor = tutorService.create(tutorDto);
        return referenceTutor.isEmpty()
                ? ResponseEntity.badRequest().build()
                : ResponseEntity.created(URI.create("/tutor/" + referenceTutor.get().getId().toString())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tutor> getOne(
            @PathVariable Long id,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String auth
    ) {
        Optional<Tutor> referenceTutor = tutorRepository.findByIdAndClinica(id, getClinicaFromRequester(auth));
        return referenceTutor.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(referenceTutor.get());
    }

    @GetMapping
    public ResponseEntity<List<Tutor>> getAll(
            @RequestHeader(AUTHORIZATION) String auth
    ) {
        List<Tutor> tutores = tutorRepository.findAllByClinica(getClinicaFromRequester(auth));
        return ResponseEntity.ok(tutores);
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
    ) {
        if (tutorRepository.existsById(id)) {
            tutorRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
