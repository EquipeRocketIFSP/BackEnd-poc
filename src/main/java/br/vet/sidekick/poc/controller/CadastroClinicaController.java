package br.vet.sidekick.poc.controller;

import br.vet.sidekick.poc.controller.dto.CadastroClinicaDto;
import br.vet.sidekick.poc.model.Clinica;
import br.vet.sidekick.poc.service.ClinicaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cadastro/clinica")
@CrossOrigin
@Slf4j
public class CadastroClinicaController {
    private static CadastroClinicaDto cadastroDto = CadastroClinicaDto.getMock();

    @Autowired
    private ClinicaService clinicaService;

    //TODO: remover as duas linhas abaixo
    private static List<CadastroClinicaDto> cadastros = new ArrayList<>();
    static {cadastros.add(cadastroDto);}

    @PostMapping
    public ResponseEntity<Clinica> registerClinica(
            @RequestBody CadastroClinicaDto cadastro){
        Optional<Clinica> clinica = clinicaService.create(cadastro);
        if (clinica.isEmpty())
            return ResponseEntity.badRequest().build();

        return ResponseEntity.created(
                URI.create("/clinica/" + clinica
                        .get().getId().toString())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CadastroClinicaDto> getOne(@PathVariable Long id){
        return id != 1L
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(cadastroDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CadastroClinicaDto>> getAll(){
        return ResponseEntity.ok(cadastros);
    }
}
