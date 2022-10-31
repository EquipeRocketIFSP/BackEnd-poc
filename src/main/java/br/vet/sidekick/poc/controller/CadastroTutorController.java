package br.vet.sidekick.poc.controller;

import br.vet.sidekick.poc.controller.dto.CadastroTutorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@RestController("/cadastro-tutor")
@CrossOrigin
public class CadastroTutorController {

    private CadastroTutorDto tutorMock = CadastroTutorDto.getMock();

    private List<CadastroTutorDto> list = Arrays.asList(tutorMock);

    @PostMapping
    public ResponseEntity<CadastroTutorDto> registerTutor(
            @RequestBody CadastroTutorDto tutor
    ){
        list.add(tutor);
        return ResponseEntity.created(
                URI.create("/tutor/" + String.valueOf(list.indexOf(tutor))
                )).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<CadastroTutorDto> getOne(
            @RequestParam Integer id
    ){
        CadastroTutorDto tutor = list.get(id);
        return tutor != null
                ? ResponseEntity.ok(tutor)
                : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<CadastroTutorDto>> getAll(){
        return list.size() == 0
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(list);
    }
}
