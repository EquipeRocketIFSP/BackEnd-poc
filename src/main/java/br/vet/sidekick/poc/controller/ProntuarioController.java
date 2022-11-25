package br.vet.sidekick.poc.controller;

import br.vet.sidekick.poc.controller.dto.ListagemProntuarioDto;
import br.vet.sidekick.poc.controller.dto.ProntuarioDto;
import br.vet.sidekick.poc.controller.dto.RecuperarProntuarioDto;
import br.vet.sidekick.poc.model.Animal;
import br.vet.sidekick.poc.model.Clinica;
import br.vet.sidekick.poc.model.Prontuario;
import br.vet.sidekick.poc.model.Veterinario;
import br.vet.sidekick.poc.repository.AnimalRepository;
import br.vet.sidekick.poc.repository.ClinicaRepository;
import br.vet.sidekick.poc.repository.ProntuarioRepository;
import br.vet.sidekick.poc.repository.VeterinarioRepository;
import br.vet.sidekick.poc.service.ClinicaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/prontuario")
public class ProntuarioController extends BaseController {

    @Autowired
    private ClinicaRepository clinicaRepository;

    @Autowired
    private VeterinarioRepository veterinarioRepository;

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private ProntuarioRepository prontuarioRepository;

    @PostMapping
    public ResponseEntity<Prontuario> create(@RequestBody ProntuarioDto prontuarioDto) {
        Prontuario prontuario = prontuarioDto.convert();

        Optional<Clinica> responseClinica = this.clinicaRepository.findById(prontuarioDto.getClinica());
        Optional<Veterinario> responseVeterinario = this.veterinarioRepository.findById(prontuarioDto.getVeterinario());
        Optional<Animal> responseAnimal = this.animalRepository.findById(prontuarioDto.getAnimal());

        System.out.println(responseClinica.isEmpty());
        System.out.println(responseVeterinario.isEmpty());
        System.out.println(responseAnimal.isEmpty());

        if (responseClinica.isEmpty() || responseVeterinario.isEmpty() || responseAnimal.isEmpty())
            return ResponseEntity.badRequest().build();

        prontuario.setClinica(responseClinica.get());
        prontuario.setVeterinario(responseVeterinario.get());
        prontuario.setAnimal(responseAnimal.get());

        prontuario = this.prontuarioRepository.save(prontuario);

        return ResponseEntity
                .created(URI.create("/prontuario/" + prontuario.getId()))
                .build();
    }

    @GetMapping
    public ResponseEntity<List<RecuperarProntuarioDto>> getAll() {
        List<RecuperarProntuarioDto> prontuarios = this.prontuarioRepository
                .findAll().stream()
                .map((prontuario) -> new RecuperarProntuarioDto(prontuario))
                .collect(Collectors.toList());

        return ResponseEntity.ok(prontuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListagemProntuarioDto> getOne(@PathVariable Long id) {
        Optional<Prontuario> responseProntuario = this.prontuarioRepository.findById(id);

        if (responseProntuario.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(new ListagemProntuarioDto(responseProntuario.get()));
    }
}