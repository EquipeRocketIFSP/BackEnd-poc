package br.vet.sidekick.poc.controller;

import br.vet.sidekick.poc.controller.dto.VeterinarioDto;
import br.vet.sidekick.poc.model.Veterinario;
import br.vet.sidekick.poc.service.VeterinarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@CrossOrigin
@RestController
@RequestMapping("/veterinario")
public class VeterinarioController {

    @Autowired
    private VeterinarioService veterinarioService;

    @PostMapping
    public ResponseEntity<Veterinario> create(
            @RequestBody VeterinarioDto veterinarioDto
    ){
        var vet = veterinarioService.create(veterinarioDto);

        return vet.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.created(URI.create("/veterinario/" + vet.get().getId())).build();
    }
}
